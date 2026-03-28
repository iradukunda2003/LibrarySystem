package com.library.librarysystem.auth;

import com.library.librarysystem.member.Member;
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

    private final com.library.librarysystem.auth.AuthService authService;

    public AuthController(com.library.librarysystem.auth.AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    @Operation(summary = "Register a new member")
    public ResponseEntity<com.library.librarysystem.auth.AuthResponse> register(
            @Valid @RequestBody Member member) {
        return new ResponseEntity<>(
                authService.register(member), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    @Operation(summary = "Login and get your token")
    public ResponseEntity<com.library.librarysystem.auth.AuthResponse> login(
            @Valid @RequestBody com.library.librarysystem.auth.AuthRequest request) {
        return new ResponseEntity<>(
                authService.login(request), HttpStatus.OK);
    }
}