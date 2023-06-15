package com.example.userservice.service;

import com.example.userservice.auth.AuthenticationRequest;
import com.example.userservice.auth.AuthenticationResponse;
import com.example.userservice.auth.RegisterRequest;
import com.example.userservice.entities.Mail;
import com.example.userservice.feign.EmailFeign;
import com.example.userservice.service.JwtService;
import com.example.userservice.entities.Role;
import com.example.userservice.entities.User;
import com.example.userservice.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final EmailFeign emailFeign;
    private final UserRepo repo;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public void register(RegisterRequest request) {
        var user = User.builder()
                .userName(request.getUserName())
                .userAddress(request.getUserAddress())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER).build();
        if (repo.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("The account is already present");
        }
        Mail mail = new Mail();
        mail.setToEmail(request.getEmail());
        mail.setBody("Welcome to the Futsal Booking System");
        mail.setMessage("Mail is sent");
        mail.setSubject("Start your journey with the first booking");
        emailFeign.sendEmail(mail);
        repo.save(user);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        ));
        var user = repo.findByEmail(request.getEmail()).orElseThrow(() -> new RuntimeException("The code is problem"));
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public void registerAdmin(RegisterRequest request) {
        var user = User.builder()
                .userName(request.getUserName())
                .userAddress(request.getUserAddress())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ADMIN).build();
        if (repo.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("The account is already present");
        }
        repo.save(user);

    }

//    public void validate(String token, AuthenticationRequest authenticationRequest) {
//        jwtService.isTokenValid(token,repo.findByEmail(authenticationRequest.getEmail()).orElseThrow(null));
//    }
}
