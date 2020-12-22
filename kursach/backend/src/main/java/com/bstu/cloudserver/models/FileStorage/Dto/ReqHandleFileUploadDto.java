package com.bstu.cloudserver.models.FileStorage.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

public class ReqHandleFileUploadDto {
    @Getter
    @Setter
    @Schema(type = "string", example = "297ec7af765bfaa301765bfdbe4b0002", description = "Токен сессии пользователя")
    String token;
    @Getter
    @Setter
    @Schema(type = "MultipartFile", format = "binary", example = "{file:\"название файла.txt\", value:\"содержимое файла\"}", description = "Загружаемый файл")
    MultipartFile file;
}
