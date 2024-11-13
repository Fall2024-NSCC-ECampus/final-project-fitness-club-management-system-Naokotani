package com.example.fitnessclub.repository;

import com.example.fitnessclub.model.ClassDate;
import com.example.fitnessclub.model.ClassDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClassDateRepository extends JpaRepository<ClassDate, Long> {
    List<ClassDate> findAllByClassDetailsId(Long classId);

    @Query("SELECT d.classDetails FROM ClassDate d WHERE d.classDetails.id = ?1")
    ClassDetails findClassDetailsByDateId(Long classId);

    @Query("FROM ClassDate date ORDER BY date.date, date.startTime")
    List<ClassDate> findClassDetailsByDateDesc();
}
