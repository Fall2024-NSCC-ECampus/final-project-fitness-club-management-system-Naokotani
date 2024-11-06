package com.example.fitnessclub.Service;

import com.example.fitnessclub.exceptions.ClassDetailsNotFound;
import com.example.fitnessclub.exceptions.ShiftNotFound;
import com.example.fitnessclub.exceptions.TrainerNotFound;
import com.example.fitnessclub.model.Shift;
import com.example.fitnessclub.repository.ShiftRepository;
import com.example.fitnessclub.request.ShiftRequest;
import org.springframework.stereotype.Service;

@Service
public class ShiftServiceImpl implements ShiftService {
    private final ShiftRepository shiftRepository;
    private final UserService userService;

    public ShiftServiceImpl(ShiftRepository shiftRepository, UserServiceImpl userService) {
        this.shiftRepository = shiftRepository;
        this.userService = userService;
    }

    @Override
    public void createShift(ShiftRequest shiftRequest)
            throws ClassDetailsNotFound, TrainerNotFound {
//        ClassDetails classDetails = findClassDetails(shiftRequest.getClassId());
//        User trainer = userService.findUsers(shiftRequest.getTrainerId());
//        shiftRepository.save(new Shift(
//                classDetails,
//                trainer,
//                shiftRequest.parseDate(),
//                shiftRequest.parseStartTime(),
//                shiftRequest.parseEndTime()
//        ));
    }

    @Override
    public Shift findShift(long id) throws ShiftNotFound {
        return shiftRepository.findById(id).orElseThrow(() -> new ShiftNotFound(id));
    }

    @Override
    public void updateShift(Shift newShift, long id) throws ShiftNotFound {
        Shift oldShift = findShift(id);
        shiftRepository.save(setShift(oldShift, newShift));
    }

    private Shift setShift(Shift oldShift, Shift newShift) {
        oldShift.setDate(newShift.getDate());
        oldShift.setStartTime(newShift.getStartTime());
        oldShift.setEndTime(newShift.getEndTime());
        oldShift.setTrainer(newShift.getTrainer());
        return oldShift;
    }
}
