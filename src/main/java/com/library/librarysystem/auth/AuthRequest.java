package com.library.librarysystem.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class AuthRequest {


    private String name;

    @NotBlank(message = "Email must not be blank")
    private String email;

    @NotBlank(message = "Password must not be blank")
    private String password;
}