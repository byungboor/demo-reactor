package com.example.demo.reactor.example;

import reactor.core.publisher.Mono;

import java.util.function.Consumer;

public class MonoApplication {

    public static Consumer<String> strPrinter = s -> System.out.println("- consume the element. element=" + s);
    public static Consumer<Throwable> errorHandler = th -> System.out.println("- onError Event. e=" + th.getMessage());
    public static Runnable completeRunner = () -> System.out.println("- end.!");

    public static void main(String[] args) {
        MonoApplication application = new MonoApplication();
        Mono<String> nameMono = application.getMono();

        System.out.println("PLEASE Check getMono() method is run lazily");

        nameMono.doOnNext(name -> System.out.println("name : " + name))
                .subscribe(strPrinter, errorHandler, completeRunner);
    }

    public Mono<String> getMono() {
        return Mono.defer(() -> {
            System.out.println("- create mono");
            return Mono.justOrEmpty("Byungboo Kim");
        });
    }
}
