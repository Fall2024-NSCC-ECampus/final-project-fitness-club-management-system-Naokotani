package com.example.fitnessclub.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserLogin implements UserDetails {
    private final User user;

    public UserLogin(User user) {
        this.user = user;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority(UserRoles.USER.name()));
        return list;
    }

    public String getPassword() {
        return user.getPassword();
    }

    public String getUsername() {
        return user.getEmail();
    }

}
