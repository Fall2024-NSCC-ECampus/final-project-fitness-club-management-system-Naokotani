package com.example.fitnessclub.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.parameters.P;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @Pattern(regexp = "^\\S+$", message="Name cannot include spaces")
    @Column(name = "first_name",nullable = false)
    private String firstName;

    @NotBlank
    @Column(name = "last_name", nullable = false)
    @Pattern(regexp = "^\\S+$", message="Name cannot include spaces")
    private String lastName;

    @NotBlank
    @Email
    @Column(name="email", nullable = false, unique = true)
    private String email;

//    @Size(min=8, message="Password must be at least 8 characters")
//    @Pattern(regexp="^(?=.*[A-Z]).+$", message="Must contain at least one upper case letter")
//    @Pattern(regexp="^(?=.*[a-z]).+$", message="Must contain at least one lower case letter")
//    @Pattern(regexp="^(?=.*[!@#$%^&]).+$", message="Must contain at least one special character !@#$%^&")
//    @Pattern(regexp="^(?=.*\\d).+$", message="Must contain at least one digit")
    @Pattern(regexp = "^\\S+$", message="Must not include spaces")
    @Column(name="password", nullable = false)
    private String password;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Role> roles = new HashSet<>();

    public User(String firstName, String lastName,
                String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public void addRole(UserRoles role) {
        roles.add(new Role(this, role));
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
