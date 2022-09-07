package com.harxsh.spring.data.jpa.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Response {

    private String message;
    private Object body;

    public Response(String message, Object body) {
        this.message = message;
        this.body = body;
    }
}
