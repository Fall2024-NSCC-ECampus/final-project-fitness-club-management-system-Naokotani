package com.example.fitnessclub.exceptions;

public class UserExists extends RuntimeException {
    public static final String message = "User already exists";

    public UserExists() {
        super(message);
    }
}
