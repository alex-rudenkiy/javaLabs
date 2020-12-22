package com.bstu.cloudserver.models.FileStorage.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public class ReqProvideListInfoDto {
    @Getter
    @Setter
    @Schema(type = "string", example = "297ec7af765bfaa301765bfdbe4b0002", description = "Токен сессии пользователя")
    String token;
}
