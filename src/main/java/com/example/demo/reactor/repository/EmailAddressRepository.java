package com.example.demo.reactor.repository;

import com.example.demo.reactor.domain.DistributionLists;
import com.example.demo.reactor.domain.EmailAddress;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class EmailAddressRepository {

    public Flux<EmailAddress> findAll() {
        return Flux.fromIterable(DistributionLists.DEV_DEPT);
    }

    public Mono<EmailAddress> findById(Integer emailId) {
        return Mono.just(DistributionLists.DEV_DEPT.get(emailId));
    }
}
