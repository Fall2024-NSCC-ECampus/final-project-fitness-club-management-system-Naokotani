package com.example.fitnessclub.Service;

import com.example.fitnessclub.exceptions.EmptyRoleSet;
import com.example.fitnessclub.exceptions.UserExists;
import com.example.fitnessclub.model.User;
import com.example.fitnessclub.model.UserRequest;
import com.example.fitnessclub.model.UserRoles;
import com.example.fitnessclub.repository.UserRepository;

public class UserRegistrationServiceImpl implements UserRegistrationService {

    @Override
    public void registerUser(UserRepository userRepository, User user) throws UserExists {
        if(userExists(userRepository, user)) {
            throw new UserExists();
        } else {
            saveUser(userRepository, user);
        }
    }

    @Override
    public void registerUser(UserRepository userRepository, UserRequest userRequest)
            throws UserExists, EmptyRoleSet {
        User user = new User(userRequest.getFirstName(),
                userRequest.getLastName(),
                userRequest.getEmail(),
                userRequest.getPassword(),
                userRequest.getRoles());

        if(userExists(userRepository, user)) {
            throw new UserExists();
        } else if(userRequest.getRoles().isEmpty()) {
            throw new EmptyRoleSet();
        } else {
            saveUser(userRepository, user);
        }
    }

    @Override
    public void registerUser(UserRepository userRepository,
                             User user, UserRoles role) throws UserExists {
        user.addRole(role);
        if(userExists(userRepository, user)) {
            throw new UserExists();
        } else {
            saveUser(userRepository, user);
        }
    }

    private void saveUser(UserRepository userRepository, User user) {
        userRepository.save(user);
    }

    private boolean userExists(UserRepository userRepository, User user) {
        return userRepository.emailExists(user.getEmail()).isPresent();
    }

}
