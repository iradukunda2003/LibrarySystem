package com.library.librarysystem;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.librarysystem.auth.AuthRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class LibrarySystemApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void whenLoginWithCorrectCredentials_thenReturnToken()
            throws Exception {

        AuthRequest loginRequest = new AuthRequest();
        loginRequest.setEmail("Claudine@gmail.com");
        loginRequest.setPassword("claudine@2003");

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper
                                .writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists())
                .andExpect(jsonPath("$.message")
                        .value("Login successful"));
    }

    @Test
    void whenLoginWithWrongPassword_thenReturn401()
            throws Exception {

        AuthRequest loginRequest = new AuthRequest();
        loginRequest.setEmail("Claudine@gmail.com");
        loginRequest.setPassword("wrongpassword");

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper
                                .writeValueAsString(loginRequest)))
                .andExpect(status().isUnauthorized());
    }


    @Test
    void whenLoginWithEmptyEmail_thenReturn400()
            throws Exception {

        AuthRequest loginRequest = new AuthRequest();
        loginRequest.setEmail("");
        loginRequest.setPassword("claudine@2003");

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper
                                .writeValueAsString(loginRequest)))
                .andExpect(status().isBadRequest());
    }
}