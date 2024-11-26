package com.example.fitnessclub.service;

import com.example.fitnessclub.exceptions.ClassDetailsNotFound;
import com.example.fitnessclub.model.ClassDate;
import com.example.fitnessclub.model.ClassDetails;
import com.example.fitnessclub.repository.ClassDateRepository;
import com.example.fitnessclub.repository.ClassRepository;
import com.example.fitnessclub.dto.ClassDateRequest;
import com.example.fitnessclub.dto.ClassRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {
    private final ClassRepository classRepository;
    private final ClassDateRepository classDateRepository;

    public ClassServiceImpl(ClassRepository classRepository, ClassDateRepository classDateRepository) {
        this.classRepository = classRepository;
        this.classDateRepository = classDateRepository;
    }

    /**
     * Finds and sorts {@link ClassDate} by date.
     * @return The sorts ClassDates.
     */
    @Override
    public List<ClassDate> findClassDateByDateDesc() {
        return classDateRepository.findClassDetailsByDateDesc();
    }

    /**
     * Finds all {@link ClassDetails}
     * @return A list of all Class details
     */
    @Override
    public List<ClassDetails> findAll() {
        return classRepository.findAll();
    }

    /**
     * Creates a new {@link ClassDetails} record.
     * @param classRequest Request for a new class
     */
    @Override
    public void createClass(ClassRequest classRequest) {
        ClassDetails classDetails = new ClassDetails(
                classRequest.getName(),
                classRequest.getDates()
        );
        classRepository.save(classDetails);
    }

    /**
     * Finds a {@link ClassDetails} by id.
     * @param id of the class to be found.
     * @return Returns the class
     * @throws ClassDetailsNotFound if the class is not found.
     */
    @Override
    public ClassDetails findClassDetailsById(Long id) throws ClassDetailsNotFound {
        return classRepository.findById(id).orElseThrow(() ->new ClassDetailsNotFound(id));
    }

    /**
     * Find a specific {@link ClassDate} by ID.
     * @param id of the class date to be found
     * @return The found class.
     */
    @Override
    public ClassDate findClassDateById(Long id) {
        return classDateRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    /**
     * Find a {@link ClassDetails} by the ID of a {@link ClassDate}.
     * @param id of the class date to find.
     * @return The found ClassDetails.
     */
    @Override
    public ClassDetails findClassDetailsByDateId(Long id) {
        return classDateRepository.findClassDetailsByDateId(id);
    }

    /**
     * Find all {@link ClassDate}.
     * @return A list of all ClassDates from the database.
     */
    @Override
    public List<ClassDate> findClassDates() {
        return classDateRepository.findAll();
    }

    //TODO handle error better

    /**
     * Create a new {@link ClassDate}
     * @param classDate The new classDate.
     * @param id of the old ClassDate.
     */
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
