package com.example.fitnessclub.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Shift {
    @Id
    private int id;

    @OneToOne
    private ClassDetails classDetails;

    @NotNull
    @OneToOne
    private User trainer;

    @NotNull
    private LocalDate date;

    @NotNull
    private LocalTime startTime;

    @NotNull
    private LocalTime endTime;

    public Shift(ClassDetails classDetails, User user, LocalDate date, LocalTime startTime, LocalTime endTime) {
        this.classDetails = classDetails;
        this.trainer = user;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Shift(User user, LocalDate date, LocalTime startTime, LocalTime endTime) {
        this.trainer = user;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
