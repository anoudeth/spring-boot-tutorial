package com.example.servicea.remote;

import com.example.servicea.config.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "api-service-b", url = "127.0.0.1:6002", configuration = FeignConfiguration.class)
public interface ApiServiceBInterface {
    @GetMapping(value = "/service-b")
    String sendSms();
}
