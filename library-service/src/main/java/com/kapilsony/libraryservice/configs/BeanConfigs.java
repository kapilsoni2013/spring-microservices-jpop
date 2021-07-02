package com.kapilsony.libraryservice.configs;

import com.kapilsony.libraryservice.exceptions.FeignErrorDecoder;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

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
                .info(new Info().title("UserService API")
                        .description("UserService Sample application")
                        .version("v1.0")
                        .license(new License().name("Epam 2.0").url("http://epam.com")))
                .externalDocs(new ExternalDocumentation()
                        .description("UserService Wiki Documentation")
                        .url("https://UserService-external-service/docs"));
    }

//    @Bean
//    public FeignErrorDecoder feignErrorDecoder(){
//        return new FeignErrorDecoder();
//    }
}
