package com.example.fitnessclub.service;

import com.example.fitnessclub.exceptions.ClassDetailsNotFound;
import com.example.fitnessclub.exceptions.ShiftNotFound;
import com.example.fitnessclub.exceptions.TrainerNotFound;
import com.example.fitnessclub.model.ClassDate;
import com.example.fitnessclub.model.ClassDetails;
import com.example.fitnessclub.model.Shift;
import com.example.fitnessclub.repository.ClassDateRepository;
import com.example.fitnessclub.repository.ClassRepository;
import com.example.fitnessclub.repository.ShiftRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShiftServiceImpl implements ShiftService {
    private final ShiftRepository shiftRepository;
    private final UserService userService;
    private final ClassService classService;
        private final ClassDateRepository classDateRepository;

    public ShiftServiceImpl(ShiftRepository shiftRepository,
                            ClassServiceImpl classServiceImpl,
                            UserServiceImpl userService, ClassDateRepository classDateRepository) {
        this.shiftRepository = shiftRepository;
        this.userService = userService;
        this.classService = classServiceImpl;
        this.classDateRepository = classDateRepository;
    }

    @Override
    public void deleteShift(Long id) {
        shiftRepository.deleteById(id);
    }

    @Override
    public List<Shift> findByTrainerId(Long trainerId) {
        return shiftRepository.findByTrainerId(trainerId);
    }

    @Override
    public List<Shift> findAll() {
        return shiftRepository.findAll();
    }

    @Override
    public List<ClassDetails> getClasses(){
        return classService.findAll();
    }

    @Override
    public void createShift(Long trainerId, Long classDateId, Long dateId)
            throws ClassDetailsNotFound, TrainerNotFound {
        Shift shift = new Shift(
                classService.findClassDetailsByDateId(classDateId),
                userService.findUserById(trainerId),
                classService.findClassDateById(dateId)
        );
        shiftRepository.save(shift);
    }

    @Override
    public List<Shift> findShiftsByTrainerId(Long trainerId) {
        return shiftRepository.findByTrainerId(trainerId);
    }

    @Override
    public Shift updateShift(ClassDate classDate) {
        Shift shift = shiftRepository.findShiftByClassDate(classDate);
        shift.setAttendance(true);
        return shiftRepository.save(shift);
    }

    @Override
    public List<ClassDate> findAvailableShifts(Long classId) {
        return classDateRepository.findAvailableShfifts(classId);
    }
}
