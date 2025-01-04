package com.sprintly.sprintly.controller;

import com.sprintly.sprintly.entity.User;
import com.sprintly.sprintly.model.auth.LoginResponse;
import com.sprintly.sprintly.model.auth.LoginUserDto;
import com.sprintly.sprintly.model.auth.RegisterUserDto;
import com.sprintly.sprintly.service.auth.UserService;
import com.sprintly.sprintly.service.auth.JwtService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Log4j2
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/register")
    public User registerUser(@RequestBody RegisterUserDto registerUserDto) {
        return userService.registerUser(registerUserDto);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = userService.authenticate(loginUserDto);
        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = LoginResponse.builder()
                .expiresIn(jwtService.getExpirationTime())
                .token(jwtToken)
                .emailID(authenticatedUser.getEmailID())
                .firstName(authenticatedUser.getFirstName())
                .lastName(authenticatedUser.getLastName())
                .build();
        return ResponseEntity.ok(loginResponse);
    }

}