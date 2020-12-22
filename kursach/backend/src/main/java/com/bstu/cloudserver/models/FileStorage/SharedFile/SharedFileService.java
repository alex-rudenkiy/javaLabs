package com.bstu.cloudserver.models.FileStorage.SharedFile;

import com.bstu.cloudserver.models.Client.Client;
import com.bstu.cloudserver.models.FileStorage.FileStorageService;
import io.minio.GetObjectArgs;
import io.minio.GetObjectResponse;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.InputStream;

import static com.bstu.cloudserver.models.FileStorage.Minio.minioClient;

@Service
public class SharedFileService {
    @Autowired
    ApplicationContext context;


    public ResponseEntity<Resource> getFile(
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

                HttpHeaders header = new HttpHeaders();
                header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+ ((GetObjectResponse) is).object());
                header.add("Cache-Control", "no-cache, no-store, must-revalidate");
                header.add("Pragma", "no-cache");
                header.add("Expires", "0");

                InputStreamResource resource = new InputStreamResource(is);

                return ResponseEntity.ok()
                        .headers(header)
                        .contentLength(((GetObjectResponse) is).headers().byteCount())
                        .contentType(MediaType.APPLICATION_OCTET_STREAM)
                        .body(resource);
            } catch (Exception e) {

            }
        } catch (Exception ex) {
            throw new RuntimeException("IOError writing file to output stream");
        }
        return null;
    }

}

