package com.example.fitnessclub.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.HashSet;
import java.util.Set;

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

    @NotBlank
    @Pattern(regexp = "^\\S+$")
    @Column(name = "first_name",nullable = false)
    private String firstName;

    @NotBlank
    @Column(name = "last_name", nullable = false)
    @Pattern(regexp = "^\\S+$")
    private String lastName;

    @NotBlank
    @Email
    @Column(name="email", nullable = false, unique = true)
    private String email;

    @Column(name="password", nullable = false)
    private String password;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private Set<Role> roles = new HashSet<>();

    public User() {}

    public User(String firstName, String lastName,
                String email, String password) {
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

    public void addRole(UserRoles role) {
        roles.add(new Role(this, role));
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
