package com.example.fitnessclub.model;

import com.example.fitnessclub.exceptions.UserExists;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
public class UserRequest {

    @NotBlank
    @Pattern(regexp = "^\\S+$", message="Name cannot include spaces")
    private final String firstName;

    @NotBlank
    @Pattern(regexp = "^\\S+$", message="Name cannot include spaces")
    private final String lastName;

    @NotBlank
    @Email
    private final String email;

//    @Size(min=8, message="Password must be at least 8 characters")
//    @Pattern(regexp="^(?=.*[A-Z]).+$", message="Must contain at least one upper case letter")
//    @Pattern(regexp="^(?=.*[a-z]).+$", message="Must contain at least one lower case letter")
//    @Pattern(regexp="^(?=.*[!@#$%^&]).+$", message="Must contain at least one special character !@#$%^&")
//    @Pattern(regexp="^(?=.*\\d).+$", message="Must contain at least one digit")
    @Pattern(regexp = "^\\S+$", message="Must not include spaces")
    private final String password;

    @NotBlank(message="Select at least one role")
    private final Set<UserRoles> roles;

    public UserRequest(String firstName, String lastName,
                       String email, String password, List<String> roles) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.roles = roleStringListToEnumSet(roles);
    }

    private Set<UserRoles> roleStringListToEnumSet(List<String> roles) throws UserExists {
        Set<UserRoles> userRoles = new HashSet<>();
        roles.forEach(role -> userRoles.add(UserRoles.valueOf(role)));
        return userRoles;
    }
}
