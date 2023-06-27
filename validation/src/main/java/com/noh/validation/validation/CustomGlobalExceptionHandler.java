package com.noh.validation.validation;

import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class CustomGlobalExceptionHandler {
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
//        log.error("Bad request der");

        Map<String, Object> resp = new LinkedHashMap<>();

//        List<String> errors = ex.getBindingResult()
//                .getFieldErrors()
//                .stream()
//                .map(x -> {
//                    return x.getField() +" => "+ x.getDefaultMessage();
//                })
//                .collect(Collectors.toList());
        Map<String, String> errors = new LinkedHashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
//            log.error("\"" +fieldName + "\" => " + errorMessage);
        });

        resp.put("resCode", "9009");
        resp.put("message", "Bad request");
        resp.put("detail", errors);
        log.error("Bad request: " + resp);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }


//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<Object> handleInternalServerError(Exception ex) {
//        Map<String, String> errors = new HashMap<>();
//        errors.put("message", "Internal Server Error der123 " + ex.getMessage());
//        System.out.println("Internal Server Error der123");
//        return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}
