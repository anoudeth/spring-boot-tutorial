package com.noh.validation.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.noh.validation.model.User;
import com.noh.validation.repo.UserRepo;
import jakarta.validation.*;
import jakarta.validation.executable.ValidateOnExecution;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/home")
public class HomeController {
    @Autowired
    UserRepo userRepo;

    @PostMapping("/user")
    public User user(@Valid @RequestBody User user) {
        log.info(">>> start HomeController.home");
        log.info("user: " + user.toString());

        return user;
    }

    @PostMapping(value = "/addUser", produces = "application/json")
    public ResponseEntity<?> addUser() {
        log.info(">>> start HomeController.addUser");
        JsonObject finalResp = new JsonObject();

        User newUser = new User();
        newUser.setName("John");
//        newUser.setEmail("nava@abc.com");
//        newUser.setAddress("123 Main St.");
        System.out.printf("user: %s\n", newUser);

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(newUser);

        if (!violations.isEmpty()) {
            JsonObject errors = new JsonObject();
            for (ConstraintViolation<User> violation : violations) {
                errors.addProperty(violation.getPropertyPath().toString(), violation.getMessage());
            }
            finalResp.addProperty("resCode", "9009");
            finalResp.addProperty("message", "some fields is mandatory");
            finalResp.add("data", errors);
            log.error("finalResp: " + finalResp);
            return new ResponseEntity<>(new Gson().toJson(finalResp), HttpStatus.OK);
        }

        System.out.printf("> saving DB");

        User output = userRepo.save(newUser);
        System.out.printf("save output: %s\n", output);

        System.out.print("> List all users:\n");
        userRepo.findAll().forEach(System.out::println);

        return new ResponseEntity<>(output, HttpStatus.OK);
    }

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ResponseStatus(HttpStatus.OK)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public Map<String, Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
//        log.error("Bad request");
//
//        Map<String, Object> resp = new LinkedHashMap<>();
//
////        List<String> errors = ex.getBindingResult()
////                .getFieldErrors()
////                .stream()
////                .map(x -> {
////                    return x.getField() +" => "+ x.getDefaultMessage();
////                })
////                .collect(Collectors.toList());
//        Map<String, String> errors = new LinkedHashMap<>();
//        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);
//            log.error("\"" +fieldName + "\" => " + errorMessage);
//        });
//
//        resp.put("resCode", "9009");
//        resp.put("message", "Bad request");
//        resp.put("detail", errors);
//        return resp;
//    }

//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @ExceptionHandler(Exception.class)
//    public Map<String, String> handleInternalServerError(Exception ex) {
//        Map<String, String> errors = new HashMap<>();
//        errors.put("message", "Internal Server Error der123");
//        System.out.println("Internal Server Error der123");
//        return errors;
//    }
}
