package com.bstu.cloudserver.models.FileStorage.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

public class ReqHandleFileDeleteDto {
    @Getter
    @Setter
    @Schema(type = "string", example = "297ec7af765bfaa301765bfdbe4b0002", description = "Токен сессии пользователя")
    String token;
}
