package com.example.demo.reactor.email;

import com.example.demo.reactor.domain.EmailAddress;
import com.example.demo.reactor.service.EmailAddressService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class EmailAddressRouter {

    private EmailAddressService emailAddressService;
    private EmailAddressRequestHandler emailAddressRequestHandler;

    public EmailAddressRouter(EmailAddressService emailAddressService,
                              EmailAddressRequestHandler emailAddressRequestHandler) {
        this.emailAddressService = emailAddressService;
        this.emailAddressRequestHandler = emailAddressRequestHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> getEmailAddresses() {
        return RouterFunctions.route(
                RequestPredicates.GET("/v2/email-addresses"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(emailAddressService.getAllEmailAddresses(), EmailAddress.class)
        );
    }

    @Bean
    public RouterFunction<ServerResponse> getEmailAddress() {
        return RouterFunctions.route(
                RequestPredicates.GET("/v2/email-addresses/{emailId}"),
                emailAddressRequestHandler::handleRequest
        );
    }

}
