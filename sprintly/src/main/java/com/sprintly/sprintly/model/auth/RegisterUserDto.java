package com.sprintly.sprintly.model.auth;

import lombok.Data;

@Data
public class RegisterUserDto {
    private String username;
    private String emailID;
    private String password;
}
