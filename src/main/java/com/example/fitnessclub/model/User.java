package com.example.fitnessclub.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @NotNull
    private String firstName;

    @NotBlank
    @NotNull
    private String lastName;

    @NotBlank
    @Email
    @NotNull
    private String email;

    @NotBlank
    @NotNull
    private String password;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();

    private final static PasswordEncoder passwordEncoder =
            PasswordEncoderFactories.createDelegatingPasswordEncoder();

    public User(String firstName, String lastName, String email, String password, Set<String> userRoles) {
        this.firstName = firstName.trim();
        this.lastName = lastName.trim();
        this.email = email.trim();
        this.password = passwordEncoder.encode(password);
        userRoles.stream().map(UserRoles::valueOf).forEach(this::addRole);
    }

    public User(String firstName, String lastName,
                String email, String password) {
        this.firstName = firstName.trim();
        this.lastName = lastName.trim();
        this.email = email.trim();
        this.password = passwordEncoder.encode(password);
    }

    public void addRole(UserRoles role) {
        roles.add(new Role(role));
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return "User{" +
                "id: " + id +
                ", firstName: " + firstName +
                ", lastName: " + lastName +
                ", email: " + email +
                '}';
    }
}
