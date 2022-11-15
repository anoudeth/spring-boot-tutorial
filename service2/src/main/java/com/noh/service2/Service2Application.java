package com.noh.service2;

import brave.sampler.Sampler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Service2Application {

    //Creates RestTemplate Object
    @Bean
    public RestTemplate rt() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(Service2Application.class, args);
    }

}
