package com.example.fitnessclub.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
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
