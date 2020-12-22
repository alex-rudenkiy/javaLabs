package com.bstu.cloudserver.models.Client.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

public class ReqLoginDto {
    @Getter
    @Setter
    @Schema(type = "string", example = "John", description = "Логин")
    public String login;
    @Getter
    @Setter
    @Schema(type = "string", example = "qwerty123", description = "Пароль")
    public String password;
    @Getter
    @Setter
    @Schema(type = "bool", example = "True", description = "Запомнить пароль")
    public Boolean remember;
}
