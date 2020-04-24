package com.example.demo.reactor.example;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MonoApplication {

    public static void main(String[] args) {
        Mono.just("value1").doOnNext(System.out::println).subscribe();
        Flux.just("value2", "value2", "value1").doOnNext(System.out::println).subscribe();
    }

}
