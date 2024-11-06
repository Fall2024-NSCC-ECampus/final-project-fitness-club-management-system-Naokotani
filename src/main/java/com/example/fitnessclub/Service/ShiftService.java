package com.example.fitnessclub.Service;

import com.example.fitnessclub.exceptions.ShiftNotFound;
import com.example.fitnessclub.model.Shift;
import com.example.fitnessclub.request.ShiftRequest;

public interface ShiftService {
    void createShift(ShiftRequest shiftRequest);
    void updateShift(Shift shift, long id);
    public Shift findShift(long id) throws ShiftNotFound;
}
