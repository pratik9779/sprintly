package com.sprintly.sprintly.model.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginUserDto {

    @Email
    @NotBlank(message = "Email id is required")
    private String emailID;

    @NotBlank(message = "Password is required")
    private String password;
}
