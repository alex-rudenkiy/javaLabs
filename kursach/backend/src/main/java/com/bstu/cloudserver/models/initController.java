package com.bstu.cloudserver.models;

import com.bstu.cloudserver.models.Promo.Promo;
import com.bstu.cloudserver.models.Promo.PromoJPA;
import io.minio.MakeBucketArgs;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import static com.bstu.cloudserver.models.FileStorage.Minio.minioClient;

@Controller
public class initController {
    @Autowired
    ApplicationContext context;

    @RequestMapping(value = "/swagger", method = RequestMethod.GET)
    public RedirectView localRedirect() {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config");
        return redirectView;
    }


    @RequestMapping(value="/api/v1/init", method=RequestMethod.GET)
    void init() throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {
        context.getBean(PromoJPA.class).save(
                new Promo(
                "Тариф базовый",
                "Доступен абсолютно бесплатно и всем!",
                "",
                500,
                0,
                true,
                true
                )
        );
        context.getBean(PromoJPA.class).save(
                new Promo(
                        "Тариф профессиональный",
                        "Доступен не абсолютно бесплатно и всем!",
                        "",
                        5000,
                        5000,
                        true,
                        true
                )
        );

        minioClient.makeBucket(
                MakeBucketArgs.builder()
                        .bucket("musicarts")
                        .build());



    }

}
