package com.example.fitnessclub.model;

import jakarta.persistence.*;

@Entity
@Table(name="users")
public class User {
//    private static final String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)" +
//            "(?=.*[@#$%^&+=!]).{8,}$";
//    private static final String passwordMessage = "Password must be at least" +
//            " 8 characters and contain one upper case, one special character," +
//            " one lower case and one digit";

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name="email", nullable = false, unique = true)
    private String email;

    @Column(name="password", nullable = false)
    private String password;

    public User() {}

    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastname) {
        this.lastName = lastname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstname) {
        this.firstName = firstname;
    }
}
