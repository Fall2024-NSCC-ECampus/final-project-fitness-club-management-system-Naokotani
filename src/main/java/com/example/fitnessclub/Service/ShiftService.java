package com.example.fitnessclub.Service;

import com.example.fitnessclub.exceptions.ShiftNotFound;
import com.example.fitnessclub.model.ClassDetails;
import com.example.fitnessclub.model.Shift;
import com.example.fitnessclub.request.ShiftRequest;

import java.util.List;

public interface ShiftService {
    void createShift(ShiftRequest shiftRequest, Long trainerId);
    void updateShift(Shift shift, long id);
    Shift findShift(long id) throws ShiftNotFound;
    List<Shift> findAll();
    List<ClassDetails> getClasses();
    List<Shift> findByTrainerId(Long trainerId);
    void deleteShift(Long id);
}
