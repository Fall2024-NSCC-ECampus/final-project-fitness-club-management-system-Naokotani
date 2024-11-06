package com.example.fitnessclub.Service;

import com.example.fitnessclub.exceptions.ClassDetailsNotFound;
import com.example.fitnessclub.model.ClassDetails;
import com.example.fitnessclub.request.ClassRequest;

public interface ClassService {
    void createClass(ClassRequest classRequest);
    void updateClassName(String newName, long id);
    void deleteClass(long id);
    ClassDetails findClassDetails(long id) throws ClassDetailsNotFound;
}
