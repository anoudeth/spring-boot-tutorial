package com.example.servicea.remote;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class ApiServiceB {

    private final ApiServiceBInterface apiSmsInterface;


    public String callServiceB() {
        log.info("> calling service B");

        String resServiceB = apiSmsInterface.sendSms();
        log.info("resServiceB: {}", resServiceB);

        return resServiceB;
    }
}
