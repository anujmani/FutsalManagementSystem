package com.example.userservice.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService service;
    @PostMapping("/register")
    public void register(@RequestBody RegisterRequest registerRequest){
        service.register(registerRequest);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(service.authenticate(request));
    }
//    @GetMapping("/validate")
//    public String validate(@RequestParam ("token") String token,@RequestBody AuthenticationRequest authenticationRequest){
//        service.validate(token,authenticationRequest);
//        return "String is valid";
//    }

}
