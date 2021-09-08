package com.ib.template.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    Logger log = LoggerFactory.getLogger(HelloController.class);

    @GetMapping("")
    private ResponseEntity<?> hello() {
        log.info("hello world");
        return ResponseEntity.ok("hello world?");
    }

    @GetMapping("/test")
    private ResponseEntity<?> test() {
        log.info("hello test");
        return ResponseEntity.ok("hello test?");
    }

}
