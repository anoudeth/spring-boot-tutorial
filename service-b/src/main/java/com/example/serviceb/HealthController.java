// src/main/java/com/example/HealthController.java
package com.example.serviceb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j

public class HealthController {

    @GetMapping("/health")
    public String health() {
        log.info("health check passed");
        return "health check passed";
    }
}