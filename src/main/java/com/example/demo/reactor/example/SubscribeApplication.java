package com.example.demo.reactor.example;

import com.example.demo.reactor.domain.DistributionLists;
import com.example.demo.reactor.domain.EmailAddress;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class SubscribeApplication {

    public static void main(String[] args) throws InterruptedException {
        Flux.fromIterable(DistributionLists.DEV_DEPT)
                .publishOn(Schedulers.parallel())
                .subscribe(new Subscriber<EmailAddress>() {

                    private Subscription subscription;
                    private int count = 0;

                    @Override
                    public void onSubscribe(Subscription s) {
                        this.subscription = s;
                        s.request(3);

                        System.out.println("onSubscribe : " + Thread.currentThread().getName());
                    }

                    @Override
                    public void onNext(EmailAddress emailAddress) {
                        count++;
                        if (count % 3 == 0)
                            subscription.request(3);
                        System.out.println("onNext : " + emailAddress.toString() + " : " + Thread.currentThread().getName());
                    }

                    @Override
                    public void onError(Throwable t) {
                        System.out.println("onError : " + t.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete");
                    }
                });

        Thread.sleep(10000);

    }
}
