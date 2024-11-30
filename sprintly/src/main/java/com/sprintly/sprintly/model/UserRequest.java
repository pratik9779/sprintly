package com.sprintly.sprintly.model;

import lombok.Data;

@Data
public class UserRequest {
    private String username;
    private String emailID;
    private String password;
}
