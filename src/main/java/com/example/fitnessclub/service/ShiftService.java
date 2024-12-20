package com.example.fitnessclub.service;

import com.example.fitnessclub.exceptions.ShiftNotFound;
import com.example.fitnessclub.model.ClassDate;
import com.example.fitnessclub.model.ClassDetails;
import com.example.fitnessclub.model.Shift;

import java.util.List;

public interface ShiftService {
    void createShift(Long trainerId, Long classID, Long dateId);
    List<Shift> findAll();
    List<ClassDetails> getClasses();
    List<Shift> findByTrainerId(Long trainerId);
    void deleteShift(Long id);
    List<Shift> findShiftsByTrainerId(Long trainerId);
    Shift updateShift(ClassDate classDate);
    List<ClassDate> findAvailableShifts(Long classId);
}
