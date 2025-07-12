package com.example.servicea.remote;

import io.micrometer.tracing.Tracer;
import io.micrometer.tracing.propagation.Propagator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;

@Slf4j
@Service
public class ApiServiceB {

    private final RestClient restClient;
    private final Tracer tracer;
    private final Propagator propagator;

    @Value("${url.service-b-host}")
    private String api_service_b_host;
    @Value("${url.service-b-path-call}")
    private String api_service_b_path_call;


    public  ApiServiceB(RestClient.Builder restClient, Tracer tracer, Propagator propagator) {
        this.restClient = restClient.build();
        this.tracer = tracer;
        this.propagator = propagator;
    }


    public String callServiceB() {
        log.info("> calling service B");

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(this.api_service_b_host)
                .path(this.api_service_b_path_call);

        log.info("> uriComponentsBuilder: {}", uriComponentsBuilder.toUriString());

        ResponseEntity<?> resEnt = restClient
                .get()
                .uri(uriComponentsBuilder.toUriString())
//                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers -> propagator.inject(Objects.requireNonNull(tracer.currentSpan()).context(), headers, (carrier, key, value) -> carrier.add(key, value)))
//                .header(HttpHeaders.AUTHORIZATION, "Basic " + Base64.getEncoder().encodeToString((this.api_fcubs_user + ":" + this.api_fcubs_password).getBytes()))
//                .body(reqBody.toString())
                .retrieve().toEntity(String.class);


        log.info(">resServiceB: {}", resEnt);

        return Objects.requireNonNull(resEnt.getBody()).toString();

    }
}
