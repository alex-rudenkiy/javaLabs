package com.bstu.cloudserver;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

public class Response {
    @Getter
    String status;
    @Getter
    Object payload;
    public Response(String status, Object payload){
        this.status = status;
        this.payload = payload;
    }
}
