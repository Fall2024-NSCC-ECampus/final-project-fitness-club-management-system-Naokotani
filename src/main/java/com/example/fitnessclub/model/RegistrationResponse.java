package com.example.fitnessclub.model;

public class RegistrationResponse {
    public final String firstName;
    public final String lastName;
    public final String email;
    private String message;

    public RegistrationResponse(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
