package com.example.demo.reactor;

public class ApiException extends RuntimeException {

    public ApiException(String message) {
        super(message);
    }
}
