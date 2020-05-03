package com.example.demo.reactor.example;

import com.example.demo.reactor.domain.EmailAddress;
import reactor.core.publisher.Flux;

import java.util.List;

public class ErrorFallbackApplication {

    public static void main(String[] args) {
        List<EmailAddress> emails = List.of(
                EmailAddress.of("jw", "test.com"),
                EmailAddress.of("kelly", "test.com")
        );

        Flux.fromIterable(emails)
                .map(emailAddress -> {
                    if (emailAddress.getLocalPart().contains("k"))
                        throw new IllegalArgumentException("K!!!!");
                    return emailAddress.toString();
                })
                // TODO 1. IllegalArgumentException 을 ApiException 으로 변경합니다.
                // TODO 2. Exception 이 발생하면 unknown@test.com 을 fallback 처리합니다.
                .subscribe(
                        System.out::println,
                        e -> e.printStackTrace()
                );

    }
}
