package com.noh.logder.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/foo")
public class FooCont {

    @GetMapping("/a")
    public ResponseEntity<?> fooa() {

        for(int i=0; i<500; i++) {
            log.info("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx " + i);
            log.info("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx " + i);
            log.info("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx " + i);
        }
        log.info("this is foo " + new Date());
        return ResponseEntity.ok("foo " + new Date());
    }
}
