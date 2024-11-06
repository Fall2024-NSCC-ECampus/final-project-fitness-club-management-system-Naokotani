package com.example.fitnessclub.exceptions;

public class ClassDetailsNotFound extends RuntimeException {
    public ClassDetailsNotFound(long id) {
        super(String.format("Class with id does not exist. Id: %d", id));
    }
}
