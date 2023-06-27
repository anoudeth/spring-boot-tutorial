package com.noh.validation.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Generated;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer recId;
    private String name;
    @NotBlank(message = "email is mandatory")
    private String email;
    @NotBlank(message = "address is mandatory")
    private String address;


    // generate json string from these properties with double quotes
    @Override
    public String toString() {
        return "{\"recId\":\"" + recId + "\"" +
                ",\"name\":\"" + name + "\"" +
                ",\"email\":\"" + email + "\"" +
                ",\"address\":\"" + address + "\"" +
                "}";
    }

}
