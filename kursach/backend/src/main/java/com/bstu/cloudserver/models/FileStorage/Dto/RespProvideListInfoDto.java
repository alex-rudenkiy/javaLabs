package com.bstu.cloudserver.models.FileStorage.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class RespProvideListInfoDto {
    @NonNull
    @Schema(type = "string", example = "myfile.txt", description = "Название файла")
    String filename;
    @NonNull
    @Schema(type = "string", example = "/myfolder/myfile.txt", description = "Полный путь к файлу")
    String filepath;
    @NonNull
    @Schema(type = "bool", example = "True", description = "Является ли объект папкой")
    Boolean isDir;
}
