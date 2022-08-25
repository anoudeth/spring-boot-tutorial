package com.noh.service2.config;

import brave.sampler.Sampler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    //Collects data from Sleuth and provides it to Zipkin Client
    @Bean
    public Sampler samplerOb() {
        //return Sampler.NEVER_SAMPLE;
        return Sampler.ALWAYS_SAMPLE;
    }


}