package com.example.servicea.controller;

import com.example.servicea.remote.ApiServiceB;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
@Slf4j
@RequestMapping("/hello")
public class HelloCont {
    private final RestClient restClient;
    private final ApiServiceB apiServiceB;

    public HelloCont(RestClient.Builder restClientBuilder, ApiServiceB apiServiceB){
        this.restClient = restClientBuilder.build();
        this.apiServiceB = apiServiceB;
    }

    @GetMapping("/call-service-b")
    public String callServiceB() {
        log.info(">>> START");
        String res = apiServiceB.callServiceB();
        log.info("> final response: " + res);
        log.info(">>> END");
        return "Service A calling Service B: " + res;
    }
@GetMapping("/world")
    public String world() {
        log.info(">>> START");
        log.info(" my world" );
        // print current thread name
        log.info("Thread: " + Thread.currentThread().getName());
        // print current thread id
        log.info("Thread ID: " + Thread.currentThread().getId());
        // print current thread priority
        log.info("Thread Priority: " + Thread.currentThread().getPriority());
        //print current thread state
        log.info("Thread State: " + Thread.currentThread().getState());
        // print current datetime
        log.info("Date: " + new java.util.Date());
        log.info(">>> END");
        return "world";
    }
}
