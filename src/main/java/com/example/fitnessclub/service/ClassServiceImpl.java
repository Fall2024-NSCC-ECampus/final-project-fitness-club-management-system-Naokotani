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
     * Deletes a {@link ClassDetails} by ID.
     * @param id The id of the class to be deleted.
     */
    @Override
    public void deleteClass(long id) {
        classRepository.deleteById(id);
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

    //TODO error handling

    /**
     * Updates the name of a {@link ClassDetails} and saves it to the database.
     * @param newName The new name for the class record
     * @param id of the class to be updated.
     * @throws ClassDetailsNotFound if the class to be updated is now found.
     */
    @Override
    public void updateClassName(String newName, long id) throws ClassDetailsNotFound {
        ClassDetails oldClass = findClassDetailsById(id);
        classRepository.save(setClassName(oldClass, newName));
    }

    /**
     * Set a new name on a {@link ClassDetails} instance.
     * @param oldClass The class to be updated.
     * @param newName The new name for the class.
     * @return returns the updated class.
     */
    private ClassDetails setClassName(ClassDetails oldClass, String newName) {
        oldClass.setName(newName);
        return oldClass;
    }

    /**
     * Finds a {@link ClassDetails} by Id.
     * @param id of the class to be found.
     * @return Returns the class
     * @throws ClassDetailsNotFound if the class is not found.
     */
    @Override
    public ClassDetails findClassDetailsById(Long id) throws ClassDetailsNotFound {
        return classRepository.findById(id).orElseThrow(() ->new ClassDetailsNotFound(id));
    }


    /**
     * Find all {@link ClassDate} for a specific {@link ClassDetails} by ID.
     * @param classId The ID of the date to find.
     * @return The found class date.
     */
    @Override
    public List<ClassDate> findClassDatesById(Long classId) {
        return classDateRepository.findAllByClassDetailsId(classId);
    }

    //TODO handle error better

    /**
     * Find a specific {@link ClassDate} by ID.
     * @param id of the class date to be found
     * @return The found class.
     */
    @Override
    public ClassDate findClassDateById(Long id) {
        return classDateRepository.findById(id).orElseThrow();
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
