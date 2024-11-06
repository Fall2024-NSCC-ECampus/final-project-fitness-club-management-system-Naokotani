package com.example.fitnessclub.security;

import com.example.fitnessclub.model.User;
import com.example.fitnessclub.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

public class UserLogin implements UserDetails {
    private final User user;
    private final UserRepository userRepository;

    public UserLogin(User user, UserRepository userRepository) {
        this.user = user;
        this.userRepository = userRepository;
    }
    @Override
    @Transactional(readOnly = true)
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userRepository.findByEmailWithRoles(user.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"))
                .getRoles().stream()
                .map(role ->
                        new SimpleGrantedAuthority("ROLE_" + role.getRole().name())).toList();
    }

    public String getPassword() {
        return user.getPassword();
    }

    public String getUsername() {
        return user.getEmail();
    }
}
