package com.example.fitnessclub.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="class_date")
public class ClassDate {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private ClassDetails classDetails;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    LocalTime time;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private User trainer;
}
