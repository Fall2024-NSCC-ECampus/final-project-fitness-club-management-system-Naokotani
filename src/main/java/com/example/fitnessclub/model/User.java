package com.example.fitnessclub.model;

import jakarta.persistence.*;

@Entity
@Table(name="users")
public class User {
    private static final String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)" +
            "(?=.*[@#$%^&+=!]).{8,}$";
    private static final String passwordMessage = "Password must be at least" +
            " 8 characters and contain one upper case, one special character," +
            " one lower case and one digit";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name="email", nullable = false, unique = true)
    private String email;

    @Column(name="password", nullable = false)
    private String password;

    public User() {}

    public User(String username, String email, String password) {
        this.username = username;
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

    public String getUsername() {
        return username;
    }
}
