package com.kapilsony.bookservice.configs;

import brave.sampler.Sampler;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.webmvc.api.OpenApiResource;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;

import java.util.Arrays;

@Configuration
public class BeanConfigs {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("BookService API")
                        .description("BookService Sample application")
                        .version("v1.0")
                        .license(new License().name("Epam 2.0").url("http://epam.com")))
                .externalDocs(new ExternalDocumentation()
                        .description("BookService Wiki Documentation")
                        .url("https://BookService-external-service/docs"));
    }

    @Bean
    public Sampler defaultSampler() {
        return Sampler.ALWAYS_SAMPLE;
    }
}
