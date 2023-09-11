package com.jakegodsall.personalblog.exception;

import org.springframework.http.HttpStatus;

public class BlogAPIException extends RuntimeException {
    private String message;

    public BlogAPIException(String message) {
        this.message = message;
    }

    public BlogAPIException(String message, String message1) {
        super(message);
        this.message = message1;
    }
    @Override
    public String getMessage() {
        return message;
    }
}
