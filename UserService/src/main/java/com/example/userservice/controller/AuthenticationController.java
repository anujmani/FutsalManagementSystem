package com.example.userservice.controller;

import com.example.userservice.auth.AuthenticationRequest;
import com.example.userservice.auth.AuthenticationResponse;
import com.example.userservice.service.AuthenticationService;
import com.example.userservice.auth.RegisterRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Validated
public class AuthenticationController {
    private final AuthenticationService service;

    @PostMapping("/register")
    public void register(@RequestBody @Valid RegisterRequest registerRequest) {
        service.register(registerRequest);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }
//    @GetMapping("/validate")
//    public String validate(@RequestParam ("token") String token,@RequestBody AuthenticationRequest
//    authenticationRequest){
//        service.validate(token,authenticationRequest);
//        return "String is valid";
//    }

}
