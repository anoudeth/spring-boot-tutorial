package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        System.out.println("hello world");
        SpringApplication.run(DemoApplication.class, args);
    }

}
