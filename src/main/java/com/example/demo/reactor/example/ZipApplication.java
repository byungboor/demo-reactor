package com.example.demo.reactor.example;

import com.example.demo.reactor.domain.DistributionLists;
import com.example.demo.reactor.domain.EmailAddress;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class ZipApplication {

    public static void main(String[] args) throws Exception {

        ZipApplication app = new ZipApplication();

        Mono<EmailAddress> mono1 = app.getEmailAddressByUserId(1);
        Mono<EmailAddress> mono2 = app.getEmailAddressByUserId(2);
        Mono<EmailAddress> mono3 = app.getEmailAddressByUserId(3);

        long beginTime = System.currentTimeMillis();
        Flux.zip(mono1, mono2, mono3)
                .doOnNext(list -> {
                    long endTime = System.currentTimeMillis();
                    System.out.println("collapsed " + (endTime - beginTime) + " ms");
                    System.out.println(list);
                })
                .subscribe();

        TimeUnit.SECONDS.sleep(10);
    }

    public Mono<EmailAddress> getEmailAddressByUserId(Integer userId) {

        int index = userId % 3;
        return Mono.justOrEmpty(DistributionLists.AD_TEAM.get(index))
                .delayElement(Duration.ofSeconds(index + 1));
    }
}
