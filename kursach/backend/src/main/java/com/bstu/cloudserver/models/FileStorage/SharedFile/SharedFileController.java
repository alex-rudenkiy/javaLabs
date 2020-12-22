package com.bstu.cloudserver.models.FileStorage.SharedFile;

import com.bstu.cloudserver.Response;
import com.bstu.cloudserver.models.FileStorage.FileStorageService;
import com.google.gson.Gson;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;

@Controller
public class SharedFileController {
    @Autowired
    ApplicationContext context;


    @Operation(summary = "Получить информацию об опубликованном файле", description = "", tags = { "Файловое пространство" })
    @RequestMapping(value = "/api/v1/shared/{sharedFileId}", method = RequestMethod.GET)
    public @ResponseBody
    String getSharedFileInfo(@PathVariable String sharedFileId) {
        SharedFile t = context.getBean(SharedFileJPA.class).getSharedFileByIdEquals(sharedFileId);
        return new Gson().toJson(new Response(t!=null?"ok":"failed",t));
    }

    @Operation(summary = "Скачать опубликованный файл", description = "", tags = { "Файловое пространство" })
    @RequestMapping(value = "/api/v1/shared/{sharedFileId}", method = RequestMethod.POST)
    public void downloadSharedFile(@PathVariable String sharedFileId, HttpServletResponse response) {
        SharedFile t = context.getBean(SharedFileJPA.class).getSharedFileByIdEquals(sharedFileId);
        context.getBean(SharedFileService.class).getFile(t.getClient(), t.getFilepath(), response);

    }
}
