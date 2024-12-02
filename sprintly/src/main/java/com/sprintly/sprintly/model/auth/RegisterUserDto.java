package com.sprintly.sprintly.model.auth;

import lombok.Data;

@Data
public class RegisterUserDto {
    private String firstName;
    private String lastName;
    private String emailID;
    private String password;
}
