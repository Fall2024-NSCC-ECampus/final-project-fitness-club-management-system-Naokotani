package com.example.fitnessclub.service;

import com.example.fitnessclub.exceptions.EmptyRoleSet;
import com.example.fitnessclub.exceptions.UserExists;
import com.example.fitnessclub.model.User;
import com.example.fitnessclub.dto.UserRequest;
import com.example.fitnessclub.model.UserRoles;
import com.example.fitnessclub.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {
    private final UserRepository userRepository;

    public UserRegistrationServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void registerUser(User user) throws UserExists {
        if(userExists(user)) {
            throw new UserExists();
        } else {
            saveUser(user);
        }
    }

    @Override
    public void registerUser(UserRequest userRequest)
            throws UserExists, EmptyRoleSet {
        User user = new User(userRequest.getFirstName(),
                userRequest.getLastName(),
                userRequest.getEmail(),
                userRequest.getPassword(),
                userRequest.getRoles());

        if(userExists(user)) {
            throw new UserExists();
        } else if(userRequest.getRoles().isEmpty()) {
            throw new EmptyRoleSet();
        } else {
            saveUser(user);
        }
    }

    @Override
    public void registerUser(User user, UserRoles role) throws UserExists {
        user.addRole(role);
        if(userExists(user)) {
            throw new UserExists();
        } else {
            saveUser(user);
        }
    }

    private void saveUser(User user) {
        userRepository.save(user);
    }

    private boolean userExists(User user) {
        return userRepository.findByEmail(user.getEmail()).isPresent();
    }
}
