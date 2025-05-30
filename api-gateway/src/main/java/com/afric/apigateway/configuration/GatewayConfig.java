package com.afric.apigateway.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder){
        return builder.routes()
                .route("auth-service",
                        r -> r.path("/auth/**")
                                .uri("lb://auth-service"))
                .route("accounting-service", r -> r.path("/account/**").uri("lb://accounting-service"))
                .build();
    }
}
