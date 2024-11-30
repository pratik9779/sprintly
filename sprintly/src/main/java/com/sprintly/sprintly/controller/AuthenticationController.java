package com.sprintly.sprintly.controller;

import com.sprintly.sprintly.entity.User;
import com.sprintly.sprintly.model.auth.LoginResponse;
import com.sprintly.sprintly.model.auth.LoginUserDto;
import com.sprintly.sprintly.model.auth.RegisterUserDto;
import com.sprintly.sprintly.service.auth.AuthenticationService;
import com.sprintly.sprintly.service.auth.JwtService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Log4j2
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/register")
    public User registerUser(@RequestBody RegisterUserDto registerUserDto) {
        return authenticationService.registerUser(registerUserDto);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {

        User authenticatedUser = authenticationService.authenticate(loginUserDto);
        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = LoginResponse.builder()
                .expiresIn(jwtService.getExpirationTime())
                .token(jwtToken)
                .emailID(loginUserDto.getEmailID())
                .build();
        return ResponseEntity.ok(loginResponse);
    }

}
