package com.example.demo.reactor.service;

import com.example.demo.reactor.domain.EmailAddress;
import com.example.demo.reactor.repository.EmailAddressRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class EmailAddressService {

    private EmailAddressRepository emailAddressRepository;

    public EmailAddressService(EmailAddressRepository emailAddressRepository) {
        this.emailAddressRepository = emailAddressRepository;
    }

    public Flux<EmailAddress> getAllEmailAddresses() {
        return emailAddressRepository.findAll();
    }
}
