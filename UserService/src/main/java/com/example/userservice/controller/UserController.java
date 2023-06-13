package com.example.userservice.controller;

import com.example.userservice.auth.AuthenticationService;
import com.example.userservice.auth.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/registerAdmin")
    public void registerAdmin(@RequestBody RegisterRequest registerRequest){
        authenticationService.registerAdmin(registerRequest);
    }


}
