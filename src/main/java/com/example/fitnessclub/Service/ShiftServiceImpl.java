package com.example.fitnessclub.Service;

import com.example.fitnessclub.exceptions.ClassDetailsNotFound;
import com.example.fitnessclub.exceptions.ShiftNotFound;
import com.example.fitnessclub.exceptions.TrainerNotFound;
import com.example.fitnessclub.model.ClassDetails;
import com.example.fitnessclub.model.Shift;
import com.example.fitnessclub.repository.ShiftRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShiftServiceImpl implements ShiftService {
    private final ShiftRepository shiftRepository;
    private final UserService userService;
    private final ClassService classService;

    public ShiftServiceImpl(ShiftRepository shiftRepository,
                            ClassServiceImpl classServiceImpl,
                            UserServiceImpl userService) {
        this.shiftRepository = shiftRepository;
        this.userService = userService;
        this.classService = classServiceImpl;
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
    public Shift findShift(long id) throws ShiftNotFound {
        return shiftRepository.findById(id).orElseThrow(() -> new ShiftNotFound(id));
    }

    @Override
    public List<Shift> findShiftsByTrainerId(Long trainerId) {
        return shiftRepository.findByTrainerId(trainerId);
    }

    @Override
    public List<Shift> findClassDateByTrainerId(Long id) {
        return shiftRepository.findClassDateByTrainerId(id);
    }

    // TODO fix this!
    @Override
    public void updateShift(Shift newShift, long id) throws ShiftNotFound {
//        Shift oldShift = findShift(id);
//        shiftRepository.save(setShift(oldShift, newShift));
    }

//    private Shift setShift(Shift oldShift, Shift newShift) {
//        oldShift.setDate(newShift.getDate());
//        oldShift.setStartTime(newShift.getStartTime());
//        oldShift.setEndTime(newShift.getEndTime());
//        oldShift.setTrainer(newShift.getTrainer());
//        return oldShift;
//    }
}
