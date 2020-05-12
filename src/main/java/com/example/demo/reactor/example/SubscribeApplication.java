package com.example.demo.reactor.example;

import com.example.demo.reactor.domain.DistributionLists;
import com.example.demo.reactor.domain.EmailAddress;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class SubscribeApplication {

    public static void main(String[] args) throws InterruptedException {
        SubscribeApplication app = new SubscribeApplication();
        app.testBasicSubscriber();
    }

    public void testBasicSubscriber() {
        Flux.fromIterable(DistributionLists.DEV_DEPT)
                .log()
//                .publishOn(Schedulers.newElastic("PublisherWorker"))
//                .subscribeOn(Schedulers.newElastic("SubscriberWorker"))
                .map(EmailAddress::toString)
                .subscribe(new BaseSubscriber<String>() {

                    @Override
                    protected void hookOnSubscribe(Subscription subscription) {
                        super.hookOnSubscribe(subscription);
                        log.info("Thread [{}], onSubscribe", Thread.currentThread().getName());
                    }

                    @Override
                    protected void hookOnNext(String value) {
                        super.hookOnNext(value);
                        log.info("Thread [{}], onNext", Thread.currentThread().getName());
                    }

                    @Override
                    protected void hookOnComplete() {
                        super.hookOnComplete();
                        log.info("Thread [{}], onComplete", Thread.currentThread().getName());
                    }

                    @Override
                    protected void hookOnError(Throwable throwable) {
                        super.hookOnError(throwable);
                        log.info("Thread [{}], onError", Thread.currentThread().getName());
                    }
                });
    }
}
