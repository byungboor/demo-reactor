package com.example.demo.reactor.example;

import com.example.demo.reactor.ApiException;
import com.example.demo.reactor.domain.EmailAddress;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.function.Function;

public class ErrorHandlingApplication {

    public Function<EmailAddress, EmailAddress> validate = emailAddress -> {
        if (emailAddress.getLocalPart().contains("k"))
            throw new IllegalArgumentException("K!!!!");
        return emailAddress;
    };

    public static void main(String[] args) {
        List<EmailAddress> emails = List.of(
                EmailAddress.of("jw", "test.com"),
                EmailAddress.of("kelly", "test.com")
        );

        ErrorHandlingApplication app = new ErrorHandlingApplication();

        Flux.fromIterable(emails)
                .doOnNext(email -> System.out.println("email element : " + email.toString()))
                .map(app.validate)
                .onErrorMap(IllegalArgumentException.class, e -> new ApiException("It is invalid email"))
                .subscribe(
                        email -> System.out.println("subscribed email : " + email.toString()),
                        e -> e.printStackTrace()
                );

    }


}
