package com.example.demo.reactor.configuration;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import java.util.concurrent.TimeUnit;

@Slf4j
@Configuration
public class WebClientConfiguration {

    @Bean
    public WebClient webClient() {
        HttpClient httpClient = HttpClient
                .create()
                .keepAlive(false)
                .tcpConfiguration(tcpClient ->
                        tcpClient
                                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000)
                                .doOnConnected(connection -> {
                                    connection.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.SECONDS));
                                    connection.addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.SECONDS));
                                })
                );

        return WebClient
                .builder()
                .defaultHeader("X-Component-Name", "AD-API")
                .filter(ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
                    log.debug("{} / {}", clientRequest.method(), clientRequest.url());
                    return Mono.just(clientRequest);
                }))
                .filter(ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
                    log.debug("statusCode : {} ", clientResponse.statusCode());
                    return Mono.just(clientResponse);
                }))
                .exchangeStrategies(ExchangeStrategies.withDefaults())
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }
}
