package com.example.fitnessclub.repository;

import com.example.fitnessclub.model.ClassDate;
import com.example.fitnessclub.model.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShiftRepository extends JpaRepository<Shift, Long> {
    List<Shift> findByTrainerId(Long trainerId);
    void deleteByTrainerId(Long trainerId);
    Shift findShiftByClassDate(ClassDate classDate);
    List<Shift> findShiftByTrainerId(Long trainerId);
}
