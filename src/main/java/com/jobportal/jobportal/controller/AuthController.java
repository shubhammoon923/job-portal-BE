package com.jobportal.jobportal.controller;

import com.jobportal.jobportal.Dto.UserRequestDTO;
import com.jobportal.jobportal.Dto.UserResponseDTO;
import com.jobportal.jobportal.dto.ApiResponse;
import com.jobportal.jobportal.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerUser(@Valid @RequestBody UserRequestDTO userRequest) {
        UserResponseDTO registeredUser = userService.registerUser(userRequest);
        return ResponseEntity.ok(new ApiResponse("User registered successfully", registeredUser));
    }
}
