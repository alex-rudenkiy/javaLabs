package com.bstu.cloudserver.models.FileStorage;

import io.minio.MinioClient;


public class Minio {
static public MinioClient minioClient =
        MinioClient.builder()
                .endpoint("http://localhost:9000")
                //.endpoint("http://filestorage:9000")
                .credentials("AKIAIOSFODNN7EXAMPLE", "wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY")
                .build();

}
