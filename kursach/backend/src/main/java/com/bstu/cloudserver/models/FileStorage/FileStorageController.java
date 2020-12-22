package com.bstu.cloudserver.models.FileStorage;

import com.bstu.cloudserver.Response;
import com.bstu.cloudserver.models.Client.Client;
import com.bstu.cloudserver.models.FileStorage.Dto.*;
import com.bstu.cloudserver.models.Session.Session;
import com.bstu.cloudserver.models.Session.SessionJPA;
import com.google.gson.Gson;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.net.URLCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import static com.bstu.cloudserver.models.FileStorage.Minio.minioClient;

@Controller
public class FileStorageController {
    @Autowired
    ApplicationContext context;

    @Operation(summary = "Получить содержимое папки", description = "", tags = { "Файловое пространство" })
    @RequestMapping(value="/api/v1/mycloud/**", method=RequestMethod.POST)
    public @ResponseBody String provideListInfo(@RequestBody ReqProvideListInfoDto data, HttpServletRequest request) throws DecoderException {
        String dirpath = "";
        if(request.getRequestURL().toString().split("/mycloud/").length>1) dirpath = new URLCodec().decode(request.getRequestURL().toString().split("/mycloud/")[1]);

        return new Gson().toJson(new Response("ok",context.getBean(FileStorageService.class).provideListInfo(data.getToken(), dirpath)));
    }

    @Operation(summary = "Загрузить файл в папку", description = "", tags = { "Файловое пространство" })
    @RequestMapping(value="/api/v1/upload/mycloud/**", method=RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public @ResponseBody String handleFileUpload(
            @ModelAttribute ReqHandleFileUploadDto data,
                                                 HttpServletRequest request) throws DecoderException {
        String dirpath = "";
        if(request.getRequestURL().toString().split("/mycloud/").length>1) {
            String tpath = request.getRequestURL().toString().split("/mycloud/")[1];

            dirpath = new URLCodec().decode(tpath);
        }

        return new Gson().toJson(new Response("ok",context.getBean(FileStorageService.class).handleFileUpload(data.getToken(), data.getFile(), dirpath)));
    }

    @Operation(summary = "Скачать файл в папку", description = "", tags = { "Файловое пространство" })
    @RequestMapping(value = "/api/v1/download/mycloud/**", method = RequestMethod.POST)
    public void getFile(
            @RequestBody ReqGetFileDto data,
            HttpServletRequest request,
            HttpServletResponse response) throws DecoderException {
        String filePath = "";
        if(request.getRequestURL().toString().split("/mycloud/").length>1) filePath = new URLCodec().decode(request.getRequestURL().toString().split("/mycloud/")[1]);


        Client owner = null;
        List<Session> t = context.getBean(SessionJPA.class).findSessionByTokenEquals(data.getToken());
        if(!t.isEmpty()){
            owner = t.get(0).getClient();
        }

        context.getBean(FileStorageService.class).getFile(owner, filePath, response);

    }

    @Operation(summary = "Создать пустую папку в папке", description = "", tags = { "Файловое пространство" })
    @RequestMapping(value = "/api/v1/mkdir/mycloud/**", method = RequestMethod.POST)
    public @ResponseBody String mkDirectory(
            @RequestBody ReqGetFileDto data,
            HttpServletRequest request,
            HttpServletResponse response) throws DecoderException {

        String dirpath = "";
        if(request.getRequestURL().toString().split("/mycloud/").length>1) dirpath = new URLCodec().decode(request.getRequestURL().toString().split("/mycloud/")[1]);

        Client c = null;

        List<Session> t = context.getBean(SessionJPA.class).findSessionByTokenEquals(data.getToken());
        if(!t.isEmpty()){
            c = t.get(0).getClient();
        }

        if (c!=null &&!dirpath.isEmpty()) {
            try {

                minioClient.putObject(
                        PutObjectArgs.builder()
                                .bucket(context.getBean(FileStorageService.class).getBucketName(c))
                                .object(dirpath)
                                .stream(
                                new ByteArrayInputStream(new byte[] {}), 0, -1)
                                .build());
                new Gson().toJson(new Response("ok",""));
            } catch (Exception e) {
                return null;
            }
        } else {
            return null;
        }
        return null;
    }

    @Operation(summary = "Удалить файл или папку", description = "", tags = { "Файловое пространство" })
    @RequestMapping(value="/api/v1/delete/mycloud/**", method = RequestMethod.POST)
    public @ResponseBody String handleFileDelete(@Parameter(description="UserSessionToken",required=true, schema=@Schema(implementation = ReqHandleFileDeleteDto.class))
                                                 @RequestBody ReqHandleFileDeleteDto data,
                                                 HttpServletRequest request) throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException, DecoderException {
        String filepath = "";
        if(request.getRequestURL().toString().split("/mycloud/").length>1) filepath = new URLCodec().decode(request.getRequestURL().toString().split("/mycloud/")[1]);

        return new Gson().toJson(new Response("ok",context.getBean(FileStorageService.class).handleFileDelete(data.getToken(), filepath)));
    }


    @Operation(summary = "Опубликовать файл", description = "", tags = { "Файловое пространство" })
    @RequestMapping(value="/api/v1/share/mycloud/**", method = RequestMethod.POST)
    @ResponseBody
    public String handleFileShare(@Parameter(description="UserSessionToken",required=true, schema=@Schema(implementation = ReqHandleFileShareDto.class))
                                                 @RequestBody ReqHandleFileShareDto data,
                                                 HttpServletRequest request) throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException, DecoderException {
        String filepath = "";
        if(request.getRequestURL().toString().split("/mycloud/").length>1) filepath = new URLCodec().decode(request.getRequestURL().toString().split("/mycloud/")[1]);

        String result = new Gson().toJson(new Response("ok",context.getBean(FileStorageService.class).handleFileShare(data.getToken(), filepath)));

        return result;
    }

}
