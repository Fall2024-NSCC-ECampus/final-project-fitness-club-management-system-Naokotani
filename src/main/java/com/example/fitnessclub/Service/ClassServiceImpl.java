package com.example.fitnessclub.Service;

import com.example.fitnessclub.exceptions.ClassDetailsNotFound;
import com.example.fitnessclub.model.ClassDetails;
import com.example.fitnessclub.repository.ClassRepository;
import com.example.fitnessclub.request.ClassRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {
    private final ClassRepository classRepository;

    public ClassServiceImpl(ClassRepository classRepository) {
        this.classRepository = classRepository;
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
    public ClassDetails findClassDetails(long id) throws ClassDetailsNotFound {
        return classRepository.findById(id).orElseThrow(() ->new ClassDetailsNotFound(id));
    }
}
