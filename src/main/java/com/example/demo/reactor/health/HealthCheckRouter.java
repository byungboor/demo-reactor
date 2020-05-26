package com.example.demo.reactor.health;

import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

// TODO-01 : Annotation which indicates that a class declares bean definition.
public class HealthCheckRouter {

    private HealthCheckRequestHandler healthCheckRequestHandler;

    public HealthCheckRouter(HealthCheckRequestHandler healthCheckRequestHandler) {
        this.healthCheckRequestHandler = healthCheckRequestHandler;
    }

    // TODO-02 :  Annotation for bean definition.
    public RouterFunction<ServerResponse> doHealthCheck() {

        // TODO-03
        // RouterFunction<ServerResponse>를 리턴합니다.
        // Request 는 /monitor/l7check uri 에 GET method 입니다. 을 처리하는 를 만들어주세요.
        // RouterFunctions.route(), RequestPredicates.GET() 그리고 HealthCheckRequestHandler의 handle 메서드를 잘 이용하면 됩니다.

        return null;
    }
}
