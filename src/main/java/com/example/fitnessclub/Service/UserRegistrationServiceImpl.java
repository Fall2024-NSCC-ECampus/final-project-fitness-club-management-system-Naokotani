package com.example.fitnessclub.Service;

import com.example.fitnessclub.exceptions.UserExists;
import com.example.fitnessclub.model.User;
import com.example.fitnessclub.model.UserRoles;
import com.example.fitnessclub.repository.UserRepository;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Set;

public class UserRegistrationServiceImpl implements UserRegistrationService {
    private final static PasswordEncoder passwordEncoder =
                PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @Override
    public void registerUser(UserRepository userRepository,
                             User user, Set<UserRoles> roles) throws UserExists {
        roles.forEach(user::addRole);
        if(userExists(userRepository, user)) {
            throw new UserExists();
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
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
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    private boolean userExists(UserRepository userRepository, User user) {
        return userRepository.emailExists(user.getEmail()).isPresent();
    }
}
