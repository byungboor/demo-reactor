package com.example.demo.reactor.controller;

import com.example.demo.reactor.domain.EmailAddress;
import com.example.demo.reactor.service.EmailAddressService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class EmailAddressController {

    private EmailAddressService emailAddressService;

    public EmailAddressController(EmailAddressService emailAddressService) {
        this.emailAddressService = emailAddressService;
    }

    @RequestMapping("/v1/emailAddresses/")
    public Flux<EmailAddress> getEmailAddresses() {
        return emailAddressService.getAllEmailAddresses();
    }
}
