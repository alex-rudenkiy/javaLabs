package com.bstu.cloudserver.models.FileStorage;

import com.bstu.cloudserver.models.Client.Client;
import com.bstu.cloudserver.models.FileStorage.Dto.RespProvideListInfoDto;
import com.bstu.cloudserver.models.FileStorage.SharedFile.SharedFile;
import com.bstu.cloudserver.models.FileStorage.SharedFile.SharedFileJPA;
import com.bstu.cloudserver.models.Session.Session;
import com.bstu.cloudserver.models.Session.SessionJPA;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.Mp3File;
import io.minio.*;
import io.minio.errors.*;
import io.minio.messages.Item;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import static com.bstu.cloudserver.models.FileStorage.Minio.minioClient;

@Service
public class FileStorageService {
    @Autowired
    ApplicationContext context;

    public String getBucketName(Client client) {
        System.out.println(client.getName());
        return client.getName().replaceAll("[^A-Za-z0-9]", "");
    }

    public List<RespProvideListInfoDto> provideListInfo(String token, String dirpath) {
        Client c = null;
        List<RespProvideListInfoDto> result = new LinkedList<>();

        List<Session> t = context.getBean(SessionJPA.class).findSessionByTokenEquals(token);
        if (!t.isEmpty()) {
            c = t.get(0).getClient();


            Iterable<Result<Item>> results = minioClient.listObjects(
                    ListObjectsArgs.builder().bucket(context.getBean(FileStorageService.class).getBucketName(c)).prefix(dirpath).build());


            results.forEach(e -> {
                try {
                    result.add(new RespProvideListInfoDto(e.get().objectName(), e.get().objectName(), e.get().isDir()));
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            });
        }

        return result;
    }

    public Boolean handleFileUpload(String token, MultipartFile file, String dirpath) {
        Client c = null;

        List<Session> t = context.getBean(SessionJPA.class).findSessionByTokenEquals(token);
        if (!t.isEmpty()) {
            c = t.get(0).getClient();
        }

        if (c != null && !file.isEmpty()) {
            File randTmpFileName = new File(String.format("tmp\\%s.dat", RandomStringUtils.random(15, true, true)));
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(randTmpFileName));
                stream.write(bytes);
                stream.close();

                System.out.println(dirpath + file.getOriginalFilename());

                minioClient.uploadObject(
                        UploadObjectArgs.builder()
                                .bucket(context.getBean(FileStorageService.class).getBucketName(c))
                                .object(dirpath + file.getOriginalFilename())
                                .filename(randTmpFileName.getAbsolutePath())
                                .build());


                if (file.getContentType().equals("audio/mpeg")) {
                    Mp3File mp3file = new Mp3File(randTmpFileName);
                    byte[] imageData = null;
                    ID3v2 id3v2Tag = null;
                    if (mp3file.hasId3v2Tag()) {
                        id3v2Tag = mp3file.getId3v2Tag();
                        imageData = id3v2Tag.getAlbumImage();
                    }

                    if (imageData != null) {
                        String mimeType = id3v2Tag.getAlbumImageMimeType();
                        String randName = String.format("%s.png", RandomStringUtils.random(15, true, true));
                        RandomAccessFile tfile = new RandomAccessFile("tmp\\" + randName, "rw");
                        tfile.write(imageData);
                        tfile.close();

                        minioClient.uploadObject(
                                UploadObjectArgs.builder()
                                        .bucket("musicarts")
                                        .object(randName)
                                        .filename("tmp\\" + randName)
                                        .build());

                        File dfile = new File(randName);
                        dfile.delete();
                    }

                }

                randTmpFileName.delete();
                //return "Вы удачно загрузили " + name + " в " + name + "-uploaded !";
                return true;

            } catch (Exception e) {
                //return "Вам не удалось загрузить " + name + " => " + e.getMessage();
                return false;
            }
        } else {
            return false;//return "Вам не удалось загрузить " + name + " потому что файл пустой.";
        }
    }

    public Boolean handleFileDelete(String token, String filepath) throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {
        Client c = null;
        List<Session> t = context.getBean(SessionJPA.class).findSessionByTokenEquals(token);
        if (!t.isEmpty()) {
            c = t.get(0).getClient();
        }

        minioClient.removeObject(
                RemoveObjectArgs.builder().bucket(context.getBean(FileStorageService.class).getBucketName(c)).object(filepath).build());
        return true;
    }

    public String handleFileShare(String token, String filepath) throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {
        Client c = null;
        List<Session> t = context.getBean(SessionJPA.class).findSessionByTokenEquals(token);
        if (!t.isEmpty()) {
            c = t.get(0).getClient();

            try {
                GetObjectResponse obj = minioClient.getObject(
                        GetObjectArgs.builder()
                                .bucket(context.getBean(FileStorageService.class).getBucketName(c))
                                .object(filepath)
                                .build());

                SharedFile sf = new SharedFile();
                sf.setClient(c);
                sf.setFilepath(filepath);
                sf.setExpires(LocalDateTime.now().plusYears(1));
                context.getBean(SharedFileJPA.class).save(sf);

                return sf.getId();

            }catch (Exception ignored){
                System.out.println("Попытка публикации чужого файла!");
            }


        }

        return "failed";
    }

    public void getFile(
            Client owner,
            String filePath,
            HttpServletResponse response) {

        try {
            try {
                InputStream is = minioClient.getObject(
                        GetObjectArgs.builder()
                                .bucket(context.getBean(FileStorageService.class).getBucketName(owner))
                                .object(filePath)
                                .build());
                response.setContentType("application/x-download");
                response.setHeader("Content-disposition", "attachment; filename=" + ((GetObjectResponse) is).object());

                System.out.println(((GetObjectResponse) is).object().toString());
                IOUtils.copy(is, response.getOutputStream());
                response.flushBuffer();
            } catch (Exception e) {

            }
        } catch (Exception ex) {
            throw new RuntimeException("IOError writing file to output stream");
        }
    }


    public Boolean checkToken(String token) {
        return false;
    }
}
