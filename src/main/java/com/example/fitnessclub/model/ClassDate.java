package com.example.fitnessclub.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class ClassDate {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private ClassDetails classDetails;
    @NotNull
    private LocalDate date;
    @NotNull
    private LocalTime startTime;
    @NotNull
    private LocalTime endTime;

    public ClassDate(ClassDetails classDetails, LocalDate date,
                     LocalTime startTime, LocalTime endTime) {
        this.classDetails = classDetails;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }


}
