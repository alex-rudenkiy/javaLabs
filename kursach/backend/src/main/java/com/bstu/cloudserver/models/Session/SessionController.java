package com.bstu.cloudserver.models.Session;

import com.bstu.cloudserver.Response;
import com.google.gson.Gson;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SessionController {
    @Autowired
    ApplicationContext context;

    @Operation(summary = "Проверить токен", description = "", tags = { "Сессия" })
    @RequestMapping(value = "/api/v1/session/checkToken", method=RequestMethod.POST)
    public @ResponseBody String auth(@RequestParam("token") String token) {
        return new Gson().toJson(new Response("ok", context.getBean(SessionService.class).checkToken(token)));
    }

}
