package com.example.demo.reactor.email;

import com.example.demo.reactor.domain.EmailAddress;
import com.example.demo.reactor.repository.EmailAddressRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
public class EmailAddressRequestHandler {

    private EmailAddressRepository emailAddressRepository;

    public EmailAddressRequestHandler(EmailAddressRepository emailAddressRepository) {
        this.emailAddressRepository = emailAddressRepository;
    }

    public Mono<ServerResponse> handleRequest(ServerRequest serverRequest) {
        Mono<EmailAddress> requestBody = Optional.ofNullable(serverRequest.pathVariable("emailId"))
                .map(Integer::valueOf)
                .map(emailAddressRepository::findById)
                .orElseThrow();

        return ServerResponse.ok().body(requestBody, EmailAddress.class);
    }
}
