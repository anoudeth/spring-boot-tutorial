package com.example.servicea.controller;

import com.example.servicea.remote.ApiServiceB;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/hello")
public class HelloCont {
    private final ApiServiceB apiServiceB;
    @GetMapping("/call-service-b")
    public String callServiceB() {
        log.info(">>> START");
        String res = apiServiceB.callServiceB();
        log.info("> final response: " + res);
        log.info(">>> END");
        return "Service A calling Service B: " + res;
    }
}
