package com.bstu.cloudserver.models.Client;

import com.bstu.cloudserver.Response;
import com.bstu.cloudserver.models.Client.dto.ReqLoginDto;
import com.bstu.cloudserver.models.Client.dto.ReqRegisterDto;
import com.bstu.cloudserver.models.Session.Session;
import com.google.gson.Gson;
import io.minio.errors.*;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Controller
@Transactional

public class ClientController {
    @Autowired
    ApplicationContext context;


/*    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "Contact not found"),
            @ApiResponse(responseCode = "405", description = "Validation exception") })*/

    @Operation(summary = "Регистрация", description = "", tags = { "Пользователь" })
    @RequestMapping(value = "/api/v1/client/register", method = RequestMethod.POST)
    public @ResponseBody String userReg(@RequestBody ReqRegisterDto data) throws IOException, InvalidResponseException, InvalidKeyException, NoSuchAlgorithmException, ServerException, ErrorResponseException, XmlParserException, InsufficientDataException, InternalException {
        Client v = context.getBean(ClientService.class).register(data);
        return new Gson().toJson(new Response(v!=null?"ok":"failed",v));
    }

    @Operation(summary = "Авторизация", description = "", tags = { "Пользователь" })
    @RequestMapping(value = "/api/v1/client/login", method = RequestMethod.POST)
    public @ResponseBody String userLogin(@RequestBody ReqLoginDto data) {//, @RequestParam("remember") String remember
        Session v = context.getBean(ClientService.class).login(data);
        return new Gson().toJson(new Response(v!=null?"ok":"failed",v));
    }

}
