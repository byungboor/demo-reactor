package com.example.demo.reactor.example;

import reactor.core.publisher.Flux;

import java.util.function.Consumer;

public class FluxApplication {

    public static Consumer<Long> integerConsumer = l -> System.out.println(">> " + l);

    public static void main(String[] args) {
        FluxApplication fluxApplication = new FluxApplication();
        fluxApplication.getIntSequenceFlux()
                .doOnNext(integerConsumer)
                .blockLast();
    }

    public Flux<Long> getIntSequenceFlux() {
        return Flux.just(1L, 2L, 3L).log();
    }
}
