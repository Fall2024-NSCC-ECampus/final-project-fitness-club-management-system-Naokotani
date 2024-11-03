package com.example.fitnessclub.model;

import com.example.fitnessclub.exceptions.UserExists;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequestScope
public class UserRequest {

    @NotBlank
    @Pattern(regexp = "^\\S+$", message="Name cannot include spaces")
    private String firstName;

    @NotBlank
    @Pattern(regexp = "^\\S+$", message="Name cannot include spaces")
    private String lastName;

    @NotBlank
    @Email
    private String email;

//    @Size(min=8, message="Password must be at least 8 characters")
//    @Pattern(regexp="^(?=.*[A-Z]).+$", message="Must contain at least one upper case letter")
//    @Pattern(regexp="^(?=.*[a-z]).+$", message="Must contain at least one lower case letter")
//    @Pattern(regexp="^(?=.*[!@#$%^&]).+$", message="Must contain at least one special character !@#$%^&")
//    @Pattern(regexp="^(?=.*\\d).+$", message="Must contain at least one digit")
    @Pattern(regexp = "^\\S+$", message="Must not include spaces")
    private String password;

    private Set<@NotBlank(message="Role must not be empty") String> roles;
}
