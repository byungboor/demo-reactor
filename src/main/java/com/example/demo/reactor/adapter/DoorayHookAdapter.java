package com.example.demo.reactor.adapter;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Component
public class DoorayHookAdapter {

    private WebClient webClient;

    public DoorayHookAdapter(WebClient webClient) {
        this.webClient = webClient;
    }

    public Optional<String> sendHook(String header, String messageBody) {
        WebClient messageClient = webClient
                .mutate()
                .baseUrl("http://hook.dooray.com").build();

        return messageClient
//                .post()
                .method(HttpMethod.POST)
                .uri("services/222/111/test")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(HookMessage.of(header, messageBody))
                .retrieve()
                .onStatus(
                        HttpStatus::is5xxServerError,
                        clientResponse -> clientResponse.bodyToMono(String.class).map(body -> new RuntimeException(body))
                )
                .onStatus(
                        HttpStatus::is4xxClientError,
                        clientResponse -> clientResponse.bodyToMono(String.class).map(body -> new RuntimeException(body))
                )
                .bodyToMono(String.class)
                .flux()
                .toStream()
                .findFirst();
    }
}
