package com.example.fitnessclub.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassDetails {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ClassDate> dates;

    public ClassDetails(String name, Set<ClassDate> dates) {
        this.name = name;
        this.dates = dates;
    }

    public ClassDetails(String name) {
        this.name = name;
    }
}
