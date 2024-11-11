package com.example.fitnessclub.service;

import com.example.fitnessclub.model.User;
import com.example.fitnessclub.dto.UserRequest;
import com.example.fitnessclub.model.UserRoles;

public interface UserRegistrationService {
    void registerUser(User user);
    void registerUser(User user, UserRoles role);
    void registerUser(UserRequest userRequest);
}
