package com.bstu.cloudserver.models.Promo;

import com.bstu.cloudserver.models.Session.SessionJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromoService {
    @Autowired
    ApplicationContext context;

    public Boolean getDefault(String token){
        return !context.getBean(SessionJPA.class).findSessionByTokenEquals(token).isEmpty();
    }

    public Promo getByName(String name){
        return context.getBean(PromoJPA.class).findByNameEquals(name).get(0);
    }

    public Promo getBySecret(String secret){
        return context.getBean(PromoJPA.class).findBySecretEquals(secret).get(0);
    }

    public List<Promo> getByDefault(Boolean value){
        return context.getBean(PromoJPA.class).findByIsDefaultEquals(value);
    }

    public List<Promo> getByEnabled(Boolean value){
        return context.getBean(PromoJPA.class).findByIsEnabledEquals(value);
    }
}
