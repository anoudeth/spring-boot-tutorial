package com.noh.service2.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/test")
public class TestCont {
    @GetMapping("/foo")
    public String testFoo() {
        log.info("test foo from service 2");
        return "test foo from service 2";
    }
}