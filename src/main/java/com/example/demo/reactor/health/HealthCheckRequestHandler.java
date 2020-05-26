package com.example.demo.reactor.health;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Component
public class HealthCheckRequestHandler {

    public Mono<ServerResponse> handle(ServerRequest request) {

        Path path = Paths.get("/Users/byungbookim-mbp/Projects/toy/demo-reactor/maintenance");
        boolean isMaintenanceMode = Files.exists(path);


        if (!isMaintenanceMode)
            return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).build();
        else
            return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).build();
        // TODO 05 -
        //  else 이후 구문을
        //  200 ok 대신 SERVICE_UNAVAILABLE 를 리턴하는 코드를 작성합니다. hint ServerResponse.status(...)

    }
}
