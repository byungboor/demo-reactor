package com.example.demo.reactor.example;

import com.example.demo.reactor.ApiException;
import com.example.demo.reactor.domain.DistributionLists;
import com.example.demo.reactor.domain.EmailAddress;
import reactor.core.publisher.Flux;

import java.util.function.Function;

public class ErrorHandlingApplication {

    public Function<EmailAddress, EmailAddress> validate = emailAddress -> {
        if (emailAddress.getLocalPart().contains("k"))
            throw new IllegalArgumentException("K!!!!");
        return emailAddress;
    };

    public static void main(String[] args) {
        ErrorHandlingApplication app = new ErrorHandlingApplication();

        Flux.fromIterable(DistributionLists.DEV_DEPT)
                .map(app.validate)
                .onErrorMap(IllegalArgumentException.class, e -> new ApiException("test"))
                .subscribe(System.out::println);

    }


}
