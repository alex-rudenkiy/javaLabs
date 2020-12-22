package com.bstu.cloudserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication()
@EnableConfigurationProperties
@EnableAutoConfiguration
public class CloudserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudserverApplication.class, args);
    }


}

