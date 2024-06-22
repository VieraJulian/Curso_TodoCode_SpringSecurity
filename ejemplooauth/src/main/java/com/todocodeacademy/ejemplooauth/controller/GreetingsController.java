package com.todocodeacademy.ejemplooauth.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("denyAll()")
public class GreetingsController {

    @GetMapping("/sayHi")
    @PreAuthorize("permitAll()")
    public String sayHi() {
        return "Hi! This is a not secured endpoint";
    }

    @GetMapping("/sayHiSecured")
    @PreAuthorize("isAuthenticated()")
    public String sayHiSecured() {
        return "Hi! This is a secured endpoint";
    }
}
