package com.sprintly.sprintly.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class Test {

    @GetMapping("/")
    public String toString() {
        return  "Hello";
    }
}
