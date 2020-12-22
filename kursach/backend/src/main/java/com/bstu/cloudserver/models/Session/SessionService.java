package com.bstu.cloudserver.models.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class SessionService {
    @Autowired
    ApplicationContext context;

    public Boolean checkToken(String token){
        return !context.getBean(SessionJPA.class).findSessionByTokenEquals(token).isEmpty();
    }
}
