package com.example.studioJudo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Setter
@Getter
@Builder
public class UserDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;

    private String password;

    private String phoneNumber;
    private Boolean isTrainer;
    private Integer qualificationId;
    private Integer roleId;

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public String setPassword(String password) {
        this.password = password;
        return password;
    }

}
