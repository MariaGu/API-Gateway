package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class APIGatewayServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(APIGatewayServiceApplication.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("eureka_route", r -> r
                        .path("/api/bookings/**", "/api/user/**")
                        .uri("lb://BOOKING-SERVICE"))
                .route("eureka_route", r -> r
                        .path("/api/hotels/**", "/api/rooms/**")
                        .uri("lb://HOTEL-SERVICE"))
                .build();
    }
}
