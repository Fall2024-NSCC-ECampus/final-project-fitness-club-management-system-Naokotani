package com.example.fitnessclub.Service;

import com.example.fitnessclub.exceptions.UserNotFound;
import com.example.fitnessclub.model.User;
import com.example.fitnessclub.request.UserRequest;

import java.util.List;

public interface UserService {
    void deleteMemeber(long id);
    void deleteTrainer(long id);
    void deleteUser(long id);
    void updateMember(UserRequest user, long id);
    void updateTrainer(UserRequest user, long id);
    List<User> findUsers();
    User findUserById(Long id) throws UserNotFound;
    UserRequest findUserRequestById(Long id) throws UserNotFound;
    List<User> findMembers();
    List<User> findTrainers();
}
