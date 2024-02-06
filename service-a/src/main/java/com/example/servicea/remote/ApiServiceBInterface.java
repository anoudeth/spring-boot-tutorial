package com.example.servicea.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "api-service-b", url = "127.0.0.1")
public interface ApiServiceBInterface {
    @GetMapping(value = "/service-b")
    String sendSms();
}
