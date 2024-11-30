package com.sprintly.sprintly.controller;

import com.sprintly.sprintly.entity.User;
import com.sprintly.sprintly.model.UserRequest;
import com.sprintly.sprintly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User createUser(@RequestBody UserRequest userRequest) {
        return userService.createUser(userRequest);
    }

    @PostMapping("/")
    public String toString() {
        return "Hello";
    }
}
