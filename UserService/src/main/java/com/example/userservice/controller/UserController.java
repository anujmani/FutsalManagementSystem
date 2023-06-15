package com.example.userservice.controller;

import com.example.userservice.service.AuthenticationService;
import com.example.userservice.auth.RegisterRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class UserController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/registerAdmin")
    public void registerAdmin(@RequestBody @Valid RegisterRequest registerRequest) {
        authenticationService.registerAdmin(registerRequest);
    }


}
