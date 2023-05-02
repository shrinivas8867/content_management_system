package com.ports.ApiGateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;


@Configuration
public class Gateway {
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder){
        return routeLocatorBuilder.routes()
                .route(
                        r->r
                                .path("/content/v1/**")
                                .uri("http://localhost:8086"))
                .route(
                        r->r
                        .path("/Content/v2/**")
                        .uri("http://localhost:8087"))
                .build();

    }
}
