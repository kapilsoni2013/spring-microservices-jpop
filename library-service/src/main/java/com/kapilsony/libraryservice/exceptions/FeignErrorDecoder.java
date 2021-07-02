package com.kapilsony.libraryservice.exceptions;

import feign.Feign;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Slf4j
public class FeignErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        if(methodKey.startsWith("UserRemoteService")){

        }
        log.info("response: "+response.reason());
        log.info("response: "+response.body().toString());
        log.info("response: "+response.status());
        log.info("response: "+response.toString());
        log.info(">>>+++methodKey "+methodKey);
        return new RuntimeException("exception from feign custom decoder");
    }
}
