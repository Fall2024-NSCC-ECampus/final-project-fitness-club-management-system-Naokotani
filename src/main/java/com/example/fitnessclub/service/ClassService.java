package com.example.fitnessclub.service;

import com.example.fitnessclub.exceptions.ClassDetailsNotFound;
import com.example.fitnessclub.model.ClassDate;
import com.example.fitnessclub.model.ClassDetails;
import com.example.fitnessclub.dto.ClassDateRequest;
import com.example.fitnessclub.dto.ClassRequest;

import java.util.List;

public interface ClassService {
    void createClass(ClassRequest classRequest);
    List<ClassDate>findClassDateByDateDesc();
    ClassDetails findClassDetailsById(Long id) throws ClassDetailsNotFound;
    List<ClassDetails> findAll();
    List<ClassDate> findClassDates();
    ClassDate findClassDateById(Long id);
    ClassDetails findClassDetailsByDateId(Long id);
    void createClassDate(ClassDateRequest classDate, Long id);

}
