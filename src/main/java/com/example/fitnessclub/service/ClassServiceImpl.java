package com.example.fitnessclub.service;

import com.example.fitnessclub.exceptions.ClassDetailsNotFound;
import com.example.fitnessclub.model.ClassDate;
import com.example.fitnessclub.model.ClassDetails;
import com.example.fitnessclub.repository.ClassDateRepository;
import com.example.fitnessclub.repository.ClassRepository;
import com.example.fitnessclub.dto.ClassDateRequest;
import com.example.fitnessclub.dto.ClassRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {
    private final ClassRepository classRepository;
    private final ClassDateRepository classDateRepository;

    public ClassServiceImpl(ClassRepository classRepository, ClassDateRepository classDateRepository) {
        this.classRepository = classRepository;
        this.classDateRepository = classDateRepository;
    }

    @Override
    public List<ClassDate> findClassDateByDateDesc() {
        return classDateRepository.findClassDetailsByDateDesc();
    }

    @Override
    public List<ClassDetails> findAll() {
        return classRepository.findAll();
    }

    @Override
    public void deleteClass(long id) {
        classRepository.deleteById(id);
    }

    @Override
    public void createClass(ClassRequest classRequest) {
        ClassDetails classDetails = new ClassDetails(
                classRequest.getName(),
                classRequest.getDates()
        );
        classRepository.save(classDetails);
    }

    //TODO error handling
    @Override
    public void updateClassName(String newName, long id) throws ClassDetailsNotFound {
        ClassDetails oldClass = findClassDetails(id);
        classRepository.save(setClassName(oldClass, newName));
    }

    private ClassDetails setClassName(ClassDetails oldClass, String newName) {
        oldClass.setName(newName);
        return oldClass;
    }

    @Override
    public ClassDetails findClassDetails(Long id) throws ClassDetailsNotFound {
        return classRepository.findById(id).orElseThrow(() ->new ClassDetailsNotFound(id));
    }

    @Override
    public List<ClassDate> findClassDatesById(Long classId) {
        return classDateRepository.findAllByClassDetailsId(classId);
    }

    //TODO handle error better
    @Override
    public ClassDate findClassDateById(Long id) {
        return classDateRepository.findById(id).orElseThrow();
    }

    @Override
    public ClassDetails findClassDetailsByDateId(Long id) {
        return classDateRepository.findClassDetailsByDateId(id);
    }

    @Override
    public List<ClassDate> findClassDates() {
        return classDateRepository.findAll();
    }

    //TODO handle error better
    @Override
    public void createClassDate(ClassDateRequest classDate, Long id) {
        classDateRepository.save(new ClassDate(
                classRepository.findById(id).orElseThrow(),
                classDate.getDate(),
                classDate.getStartTime(),
                classDate.getEndTime()
        ));
    }
}
