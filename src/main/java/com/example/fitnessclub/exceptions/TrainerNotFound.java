package com.example.fitnessclub.exceptions;

public class TrainerNotFound extends RuntimeException {
    public TrainerNotFound(long id) {
        super(String.format("Trainer not found with that Id: %d", id));
    }
}
