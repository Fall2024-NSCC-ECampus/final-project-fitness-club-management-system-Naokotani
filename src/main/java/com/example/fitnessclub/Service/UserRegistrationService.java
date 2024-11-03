package com.example.fitnessclub.Service;

import com.example.fitnessclub.model.User;
import com.example.fitnessclub.model.UserRequest;
import com.example.fitnessclub.model.UserRoles;
import com.example.fitnessclub.repository.UserRepository;

import java.util.Set;

public interface UserRegistrationService {
    void registerUser(UserRepository userRepository, User user);
    void registerUser(UserRepository userRepository, User user, UserRoles role);
    void registerUser(UserRepository userRepository, UserRequest userRequest);

}
