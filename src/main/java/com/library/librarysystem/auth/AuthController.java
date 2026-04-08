package com.library.librarysystem.auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Auth API", description = "Register and Login")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/register")
    @Operation(summary = "Register a new member")
    public ResponseEntity<AuthResponse> register(
            @Valid @RequestBody AuthRequest request) {
        return new ResponseEntity<>(
                authService.register(request), HttpStatus.CREATED);
    }


    @PostMapping("/login")
    @Operation(summary = "Login and get your token")
    public ResponseEntity<AuthResponse> login(
            @Valid @RequestBody AuthRequest request) {
        return new ResponseEntity<>(
                authService.login(request), HttpStatus.OK);
    }
}