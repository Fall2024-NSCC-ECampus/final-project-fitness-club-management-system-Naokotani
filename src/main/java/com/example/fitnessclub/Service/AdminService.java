package com.example.fitnessclub.Service;

import com.example.fitnessclub.model.Shift;
import com.example.fitnessclub.model.User;

public interface AdminService {
    void addmember(User user);
    void deletememeber(long id);
    void addtrainer(User user);
    void deletetrainer(long id);
    void updatemember(User user);
    void updatetrainer(User user);
    void createshift(long id);
    void updateshift(Shift shift);
}
