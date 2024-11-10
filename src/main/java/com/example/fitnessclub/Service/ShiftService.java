package com.example.fitnessclub.Service;

import com.example.fitnessclub.exceptions.ShiftNotFound;
import com.example.fitnessclub.model.ClassDetails;
import com.example.fitnessclub.model.Shift;

import java.util.List;

public interface ShiftService {
    void createShift(Long trainerId, Long classID, Long dateId);
    void updateShift(Shift shift, long id);
    Shift findShift(long id) throws ShiftNotFound;
    List<Shift> findAll();
    List<ClassDetails> getClasses();
    List<Shift> findByTrainerId(Long trainerId);
    void deleteShift(Long id);
    List<Shift> findShiftsByTrainerId(Long trainerId);
}
