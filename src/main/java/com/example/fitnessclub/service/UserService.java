package com.example.fitnessclub.service;

import com.example.fitnessclub.exceptions.UserNotFound;
import com.example.fitnessclub.model.User;
import com.example.fitnessclub.dto.UserRequest;

import java.util.List;

public interface UserService {
    void deleteUser(long id);
    void updateMember(UserRequest user, long id);
    List<User> findUsers();
    User findUserById(Long id) throws UserNotFound;
    List<User> findMembers();
    List<User> findTrainers();
    User findUserByEmail(String email);
}
