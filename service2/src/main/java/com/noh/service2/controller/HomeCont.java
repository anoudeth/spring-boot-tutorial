package com.noh.service2.controller;

import brave.sampler.Sampler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
public class HomeCont {
    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("/sv2")
    public String greeting() {
        log.info("Inside microservice#2");
//        String response = restTemplate.getForObject("http://localhost:8083/m3", String.class);
//        log.info("response by microservice#2 " + response);
        return "returning from microservice#2";
    }
}
