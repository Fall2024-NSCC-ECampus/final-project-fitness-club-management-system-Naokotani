package com.example.fitnessclub.exceptions;

public class UserNotFound extends RuntimeException {
    public UserNotFound(long id) {
        super(String.format("User not found with that Id: %d", id));
    }
}
