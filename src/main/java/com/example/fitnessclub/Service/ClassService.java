package com.example.fitnessclub.Service;

import com.example.fitnessclub.exceptions.ClassDetailsNotFound;
import com.example.fitnessclub.model.ClassDate;
import com.example.fitnessclub.model.ClassDetails;
import com.example.fitnessclub.request.ClassDateRequest;
import com.example.fitnessclub.request.ClassRequest;

import java.util.List;

public interface ClassService {
    void createClass(ClassRequest classRequest);
    void updateClassName(String newName, long id);
    void deleteClass(long id);
    ClassDetails findClassDetails(Long id) throws ClassDetailsNotFound;
    List<ClassDetails> findAll();
    //TODO what id?
    List<ClassDate> findClassDatesById(Long id);
    List<ClassDate> findClassDates();
    ClassDate findClassDateById(Long id);
    ClassDetails findClassDetailsByDateId(Long id);
    void createClassDate(ClassDateRequest classDate, Long id);
}
