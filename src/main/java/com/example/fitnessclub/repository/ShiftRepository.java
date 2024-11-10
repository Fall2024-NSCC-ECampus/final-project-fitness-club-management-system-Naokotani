package com.example.fitnessclub.repository;

import com.example.fitnessclub.model.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShiftRepository extends JpaRepository<Shift, Long> {
    @Query("SELECT s FROM Shift s WHERE s.trainer.id = ?1")
    List<Shift> findByTrainerId(Long id);
}
