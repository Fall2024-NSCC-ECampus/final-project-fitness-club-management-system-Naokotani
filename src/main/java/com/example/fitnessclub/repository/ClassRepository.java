package com.example.fitnessclub.repository;

import com.example.fitnessclub.model.ClassDate;
import com.example.fitnessclub.model.ClassDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassRepository extends JpaRepository<ClassDetails, Long> {

}

