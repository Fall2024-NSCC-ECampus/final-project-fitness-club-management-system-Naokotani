package com.example.fitnessclub.service;

import com.example.fitnessclub.exceptions.TrainerNotFound;
import com.example.fitnessclub.exceptions.UserNotFound;
import com.example.fitnessclub.model.Role;
import com.example.fitnessclub.model.User;
import com.example.fitnessclub.model.UserRoles;
import com.example.fitnessclub.repository.AttendanceRepository;
import com.example.fitnessclub.repository.ShiftRepository;
import com.example.fitnessclub.repository.UserRepository;
import com.example.fitnessclub.dto.UserRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ShiftRepository shiftRepository;
    private final AttendanceRepository attendanceRepository;

    public UserServiceImpl(UserRepository userRepository, ShiftRepository shiftRepository, AttendanceRepository attendanceRepository) {
        this.userRepository = userRepository;
        this.shiftRepository = shiftRepository;
        this.attendanceRepository = attendanceRepository;
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
    @Transactional
    public void deleteUser(long id) throws UserNotFound {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFound(id));
        Set<Role> roles = user.getRoles();
        roles.stream().filter(role -> role.getRole() == UserRoles.TRAINER)
                .forEach(role -> shiftRepository.deleteByTrainerId(user.getId()));
        roles.stream().filter(role -> role.getRole() == UserRoles.MEMBER)
                .forEach(role -> attendanceRepository.deleteByMemberId(user.getId()));
        userRepository.deleteById(id);
    }

    @Override
    public void updateMember(UserRequest user, long id) {
        User member = findTrainer(id);
        userRepository.save(updateUser(member, user));
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

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow();
    }
}
