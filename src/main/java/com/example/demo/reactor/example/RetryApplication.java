package com.example.demo.reactor.example;

import reactor.core.publisher.Mono;

import java.util.function.Function;

public class RetryApplication {

    private static final Function<String, String> doUpperCase = s -> {
        if (true)
            throw new IllegalArgumentException("Error Generate");
        return s;
    };

    public static void main(String[] args) {
        Mono.just("test").log()
                .map(doUpperCase)
                .retry(3)
                .doOnError(e -> System.out.println("Error is generated : " + e.getMessage()))
                .subscribe();
    }
}
