package com.example.fitnessclub.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
