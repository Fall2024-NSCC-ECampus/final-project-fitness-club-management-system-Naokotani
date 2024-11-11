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

    @OneToMany
    private Set<User> members;

    public Attendance(ClassDate classDate,
                      Set<User> members) {
        this.classDate = classDate;
        this.members = members;
    }
}
