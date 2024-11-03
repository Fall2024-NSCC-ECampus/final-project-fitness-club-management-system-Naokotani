package com.example.fitnessclub.exceptions;

public class EmptyRoleSet extends RuntimeException {
    private static final String message = "Must select at least one role.";
    public EmptyRoleSet() {
        super(message);
    }
}
