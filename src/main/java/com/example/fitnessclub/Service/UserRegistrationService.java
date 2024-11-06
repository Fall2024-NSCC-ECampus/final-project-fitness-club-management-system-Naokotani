package com.example.fitnessclub.Service;

import com.example.fitnessclub.model.User;
import com.example.fitnessclub.request.UserRequest;
import com.example.fitnessclub.model.UserRoles;

public interface UserRegistrationService {
    void registerUser(User user);
    void registerUser(User user, UserRoles role);
    void registerUser(UserRequest userRequest);
}
