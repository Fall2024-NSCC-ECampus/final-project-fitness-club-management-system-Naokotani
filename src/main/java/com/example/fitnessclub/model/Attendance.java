package com.example.fitnessclub.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name="attendance")
public class Attendance {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private ClassDate classDate;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<User> trainers;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<User> members;
}
