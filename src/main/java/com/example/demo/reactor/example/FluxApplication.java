package com.example.demo.reactor.example;

import reactor.core.publisher.Flux;

import java.util.function.Consumer;

public class FluxApplication {

    public static Consumer<Integer> integerConsumer = null; // TODO #2 fix it!

    public static void main(String[] args) {
        FluxApplication fluxApplication = new FluxApplication();
        fluxApplication.getIntSequenceFlux()
                .doOnNext(integerConsumer)
                .subscribe();
    }

    public Flux<Integer> getIntSequenceFlux() {
        return Flux.empty(); // <- TODO #1 create integer sequence flux with interval() or range();
    }
}
