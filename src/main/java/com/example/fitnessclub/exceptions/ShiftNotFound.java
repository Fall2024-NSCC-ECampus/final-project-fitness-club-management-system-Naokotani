package com.example.fitnessclub.exceptions;

public class ShiftNotFound extends RuntimeException {
    public ShiftNotFound(long id) {
        super(String.format("Shift with id not found. Id: %d", id));
    }
}
