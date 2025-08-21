package com.example.StudentJWT.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/demo")
public class DemoController {

    @GetMapping("/profile")
    public ResponseEntity<String> getProfile() {
        return ResponseEntity.ok("Profile access successful");
    }

    @GetMapping("/course")
    public ResponseEntity<String> getCourse() {
        return ResponseEntity.ok("Course access successful");
    }


}