package com.noh.configclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/rest")
@RefreshScope
@RestController
public class MessageController {

    @Value("${message}")
    private String msg;

    @GetMapping("/msg")
    public String message() {
        System.out.println("hello " + this.msg);
        return msg;
    }

}
