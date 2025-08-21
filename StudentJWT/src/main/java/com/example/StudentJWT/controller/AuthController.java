package com.example.StudentJWT.controller;

import com.example.StudentJWT.dto.AuthResponse;
import com.example.StudentJWT.dto.LoginRequest;
import com.example.StudentJWT.dto.RegisterRequest;
import com.example.StudentJWT.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(               //This declares the method register.
                                                                //Return type is ResponseEntity<AuthResponse>:
            @RequestBody RegisterRequest request                //AuthResponse is the type of data that will be returned

    //This tells Spring to take the incoming JSON payload from the POST request body and map it to a RegisterRequest object.
    ) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody LoginRequest request
    ) {
        return ResponseEntity.ok(authService.login(request));
    }
}