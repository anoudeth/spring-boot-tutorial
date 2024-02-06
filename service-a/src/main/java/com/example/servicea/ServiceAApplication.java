package com.example.servicea;

import com.example.servicea.remote.ApiServiceB;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;

@SpringBootApplication
@RestController
@Slf4j
@EnableFeignClients
public class ServiceAApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceAApplication.class, args);
    }

    @GetMapping("/service-a")
    public String test() throws Exception {
        // just to show that we are running on different hosts
        String hostname  = InetAddress.getLocalHost().getHostName();
        log.info("Service A from host: " + hostname);
        return "Service A from host: " + hostname;
    }
}
