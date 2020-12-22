package com.bstu.cloudserver.models.Client.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

public class ReqRegisterDto {
    @Getter
    @Setter
    @Schema(type = "string", example = "John", description = "Логин")
    public String login;
    @Getter
    @Setter
    @Schema(type = "string", example = "John@hotmail.com", description = "Электронная почта")
    public String email;
    @Getter
    @Setter
    @Schema(type = "string", example = "qwerty123", description = "Пароль")
    public String password;
    @Getter
    @Setter
    @Schema(type = "string", example = "BlackFriday666", description = "Промокод")
    public String promo;
}
