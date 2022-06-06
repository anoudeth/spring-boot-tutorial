package com.noh.logder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;

@RestController
@SpringBootApplication
public class LogderApplication {

    public static void main(String[] args) throws UnknownHostException {
        System.out.println("Set log file name");

        String hostName = "mon";
        hostName = InetAddress.getLocalHost().getHostName();
//		System.setProperty("log_name", hostName + System.currentTimeMillis());
        System.setProperty("log_name", hostName);

        SpringApplication.run(LogderApplication.class, args);
    }

}
