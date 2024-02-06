package com.example.servicea.config;

import feign.Capability;
import feign.micrometer.MicrometerCapability;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfiguration {
    @Bean
    public Capability capability() {
        return new MicrometerCapability();
    }
}

