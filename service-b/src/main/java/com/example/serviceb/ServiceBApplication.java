package com.example.serviceb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;

@SpringBootApplication
@RestController
@Slf4j
public class ServiceBApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceBApplication.class, args);
    }

    @GetMapping("/service-b")
    public String test() throws Exception {

        // to print the hostname of the host where the service is running
        String hostname  = InetAddress.getLocalHost().getHostName();
        log.info("Service B from host: " + hostname);
        return "Service B from host: " + hostname;
    }
}
