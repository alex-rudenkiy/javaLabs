package com.bstu.cloudserver.models.Client;

import com.bstu.cloudserver.models.Client.dto.ReqLoginDto;
import com.bstu.cloudserver.models.Client.dto.ReqRegisterDto;
import com.bstu.cloudserver.models.FileStorage.FileStorageService;
import com.bstu.cloudserver.models.Session.Session;
import com.bstu.cloudserver.models.Session.SessionJPA;
import io.minio.MakeBucketArgs;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.bstu.cloudserver.models.FileStorage.Minio.minioClient;

@Service
public class ClientService {
    @Autowired
    ApplicationContext context;

    Client register(ReqRegisterDto data){
        Client result = null;
        Client client = new Client(data.login, data.email, DigestUtils.md5Hex( data.password ));


        if(!context.getBean(ClientJPA.class).existsByName(data.login) && !context.getBean(ClientJPA.class).existsByEmail(data.email)) {

            result = context.getBean(ClientJPA.class).save(client);

            try {
                minioClient.makeBucket(
                        MakeBucketArgs.builder()
                                .bucket(context.getBean(FileStorageService.class).getBucketName(result))
                                .build());
            }catch (Exception e){
                context.getBean(ClientJPA.class).delete(client);
            }

        }




        return result;
    }

    Session login(ReqLoginDto data){
        Client u = null;

        if(context.getBean(ClientJPA.class).existsByName(data.login) && context.getBean(ClientJPA.class).existsByPasshash(DigestUtils.md5Hex(data.password))) {

            u = context.getBean(ClientJPA.class).findClientByNameEquals(data.login).get(0);

            LocalDateTime actualDateTime = LocalDateTime.now();

            if (data.remember) {
                actualDateTime = actualDateTime.plusHours(1);
            } else {
                actualDateTime = actualDateTime.plusYears(1);
            }

            context.getBean(SessionJPA.class).deleteAllByClientEquals(u);
            return context.getBean(SessionJPA.class).save(new Session(u, actualDateTime));
        }else {

            return null;


        }

    }


}
