package com.hobbyclass.backendfinal.controller;

import com.hobbyclass.backendfinal.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login/user")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String password = body.get("password");
        return authService.authenticateUser(username, password)
                .<ResponseEntity<?>>map(user -> ResponseEntity.ok(user))
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials"));
    }

    @PostMapping("/login/mentor")
    public ResponseEntity<?> loginMentor(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String password = body.get("password");
        return authService.authenticateMentor(username, password)
                .<ResponseEntity<?>>map(mentor -> ResponseEntity.ok(mentor))
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials"));
    }
}
