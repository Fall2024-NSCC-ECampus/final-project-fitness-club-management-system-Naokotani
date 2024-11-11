package com.example.fitnessclub.service;

import com.example.fitnessclub.exceptions.TrainerNotFound;
import com.example.fitnessclub.exceptions.UserNotFound;
import com.example.fitnessclub.model.User;
import com.example.fitnessclub.model.UserRoles;
import com.example.fitnessclub.repository.UserRepository;
import com.example.fitnessclub.dto.UserRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserRequest findUserRequestById(Long id) throws UserNotFound {
        return new UserRequest(findUserById(id));
    }

    @Override
    public List<User> findUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(Long id) throws UserNotFound {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFound(id));
    }

    @Override
    public List<User> findTrainers(){
        return userRepository.findUsersByRole(UserRoles.TRAINER).orElseThrow();
    }

    @Override
    public List<User> findMembers() {
        return userRepository.findUsersByRole(UserRoles.MEMBER).orElseThrow();
    }

    @Override
    public void deleteMemeber(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void deleteTrainer(long id) {
       userRepository.deleteById(id);
    }

    @Override
    public void deleteUser(long id) throws UserNotFound {
        userRepository.findById(id).orElseThrow(() -> new UserNotFound(id));
        userRepository.deleteById(id);
    }

    @Override
    public void updateMember(UserRequest user, long id) {
        User member = findTrainer(id);
        userRepository.save(updateUser(member, user));
    }

    @Override
    public void updateTrainer(UserRequest user, long id) throws TrainerNotFound {
        User trainer = findTrainer(id);
        userRepository.save(updateUser(trainer, user));
    }

    private User updateUser(User oldUser, UserRequest newUser) {
        oldUser.setFirstName(newUser.getFirstName());
        oldUser.setLastName(newUser.getLastName());
        oldUser.setEmail(newUser.getEmail());
        return oldUser;
    }

    private User findTrainer(long id) throws TrainerNotFound {
        return userRepository.findById(id).orElseThrow(() -> new TrainerNotFound(id));
    }
}
