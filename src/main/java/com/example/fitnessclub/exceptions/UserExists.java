package com.example.fitnessclub.exceptions;

public class UserExists extends RuntimeException {
    public static final String message = "User with that email already exists.";

    public UserExists() {
        super(message);
    }
}
