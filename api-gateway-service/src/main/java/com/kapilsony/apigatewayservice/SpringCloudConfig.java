//package com.kapilsony.apigatewayservice;
//
//import org.springframework.cloud.gateway.route.RouteLocator;
//import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class SpringCloudConfig {
//
//    @Bean
//    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route(r -> r.path("/library-service/**")
//                        .uri("lb://LIBRARY-SERVICE"))
//
//                .route(r -> r.path("/consumer/**")
//                        .uri("lb://SECOND-SERVICE"))
//                .build();
//    }
//
//}