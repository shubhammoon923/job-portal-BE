package com.jobportal.jobportal.service;

import com.jobportal.jobportal.Dto.UserRequestDTO;
import com.jobportal.jobportal.Dto.UserResponseDTO;
import com.jobportal.jobportal.Entity.User;
import com.jobportal.jobportal.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;  // To hash passwords

    public UserResponseDTO registerUser(UserRequestDTO userRequest) {
        // Check if user with email already exists
        Optional<User> existingUser = userRepo.findByEmail(userRequest.getEmail());
        if (existingUser.isPresent()) {
            throw new RuntimeException("User with email " + userRequest.getEmail() + " already exists.");
        }

        // Create a new User entity from the DTO
        User user = new User();
        user.setUserName(userRequest.getUserName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());  // Hash the password
        user.setPhoneNumber(userRequest.getPhoneNumber());
        user.setRole(User.Role.valueOf(userRequest.getRole().toUpperCase()));  // Set user role

        // Save user to the database
        User savedUser = userRepo.save(user);

        // Map saved user to response DTO
        UserResponseDTO userResponse = new UserResponseDTO();
        userResponse.setUserId(savedUser.getUserId());
        userResponse.setUserName(savedUser.getUserName());
        userResponse.setEmail(savedUser.getEmail());
        userResponse.setPhoneNumber(savedUser.getPhoneNumber());
        userResponse.setRole(savedUser.getRole().toString());
        userResponse.setEmailVerified(savedUser.isEmailVerified());
        userResponse.setStatus(savedUser.getStatus().toString());
        userResponse.setCreatedAt(savedUser.getCreatedAt());
        userResponse.setUpdatedAt(savedUser.getUpdatedAt());

        return userResponse;  // Return the mapped response DTO
    }
}
