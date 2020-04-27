package com.example.demo.reactor.example;

import com.example.demo.reactor.domain.DistributionLists;
import com.example.demo.reactor.domain.EmailAddress;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;

public class ScheduleApplication {

    public static Consumer<String> threadNamePrinter = s -> System.out.println("Thread:" + Thread.currentThread().getName() + ", s=" + s);
    public static Function<EmailAddress, String> converter = emailAddress -> {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return emailAddress.toString();
    };

    public static void main(String[] args) throws InterruptedException {

        System.out.println("# of members : " + DistributionLists.DEV_DEPT.size());

        Flux.fromIterable(DistributionLists.DEV_DEPT)
                .map(converter)
                .subscribeOn(Schedulers.parallel()) // async or sync ?
                .doOnNext(threadNamePrinter)
                .subscribe();

        System.out.println("DONE --------------------------");
        TimeUnit.SECONDS.sleep(10);
        System.out.println("Finished --------------------------");
    }


}
