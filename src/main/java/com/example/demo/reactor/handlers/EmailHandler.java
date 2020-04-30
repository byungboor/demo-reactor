package com.example.demo.reactor.handlers;

import com.example.demo.reactor.domain.EmailAddress;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.Objects;

@Component
public class EmailHandler {

    public Flux<EmailAddress> normalize(Flux<EmailAddress> emailAddressFlux) {
        if (Objects.isNull(emailAddressFlux))
            throw new IllegalArgumentException("flux is null");

        return emailAddressFlux
                .map(emailAddress -> EmailAddress.of(
                        emailAddress.getLocalPart().toUpperCase(),
                        emailAddress.getDomainPart().toUpperCase())
                );

    }
}
