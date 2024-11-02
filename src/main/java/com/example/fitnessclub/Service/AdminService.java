package com.example.fitnessclub.Service;

import com.example.fitnessclub.model.Shift;
import com.example.fitnessclub.model.User;

public interface AdminService {
    void addMember(User user);
    void deleteMemeber(long id);
    void addTrainer(User user);
    void deleteTrainer(long id);
    void updateMember(User user);
    void updateTrainer(User user);
    void createShift(long id);
    void updateShift(Shift shift);
}
