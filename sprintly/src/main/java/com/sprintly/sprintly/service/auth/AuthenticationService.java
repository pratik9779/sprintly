package com.sprintly.sprintly.service.auth;

import com.sprintly.sprintly.entity.User;
import com.sprintly.sprintly.model.auth.LoginUserDto;
import com.sprintly.sprintly.model.auth.RegisterUserDto;

import java.util.List;

public interface AuthenticationService {

    User registerUser(RegisterUserDto registerUserDto);
    User authenticate(LoginUserDto input);

    List<String> getUserAccounts(String username);
}
