package com.example.demo.reactor.adapter;

import com.example.demo.reactor.domain.DistributionLists;
import com.example.demo.reactor.domain.EmailAddress;
import reactor.core.publisher.Flux;

import java.util.List;

public class AccountAdapter {

    public Flux<EmailAddress> getEmailAddressByUserIds(List<Long> userIds) {

        if (userIds.size() % 3 == 0)
            throw new RuntimeException("ConnectionTimeout");

        return Flux.fromIterable(DistributionLists.DEV_DEPT);
    }
}
