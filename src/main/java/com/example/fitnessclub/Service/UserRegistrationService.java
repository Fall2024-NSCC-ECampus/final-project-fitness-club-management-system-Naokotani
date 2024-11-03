package com.example.fitnessclub.Service;

import com.example.fitnessclub.model.User;
import com.example.fitnessclub.model.UserRoles;
import com.example.fitnessclub.repository.UserRepository;

import java.util.Set;

public interface UserRegistrationService {
    void registerUser(UserRepository userRepository, User user, Set<UserRoles> roles);
    void registerUser(UserRepository userRepository, User user, UserRoles role);
}
