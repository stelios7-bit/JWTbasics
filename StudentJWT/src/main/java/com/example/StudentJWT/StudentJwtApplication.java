package com.example.StudentJWT; // Or your actual main package

import com.example.StudentJWT.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class StudentJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentJwtApplication.class, args);
    }
}