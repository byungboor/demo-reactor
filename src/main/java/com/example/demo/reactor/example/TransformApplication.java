package com.example.demo.reactor.example;

import com.example.demo.reactor.domain.DistributionLists;
import com.example.demo.reactor.domain.EmailAddress;
import com.example.demo.reactor.domain.User;
import reactor.core.publisher.Flux;

import java.util.Objects;
import java.util.function.Consumer;

public class TransformApplication {

    private static final Consumer<EmailAddress> LOGGING = emailAddress ->
            System.out.println("-- Email :" + emailAddress.toString());

    public static void main(String[] args) {
        Flux.fromIterable(DistributionLists.AD_TEAM)
                .filter(Objects::nonNull)
                .map(email -> User.of(email.getLocalPart(), email))
                .subscribe(System.out::println);
    }
}
