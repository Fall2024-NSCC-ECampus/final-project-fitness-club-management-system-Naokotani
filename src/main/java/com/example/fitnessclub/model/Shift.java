package com.example.fitnessclub.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Shift {
    @Id
    private int id;

    @NotNull
    @OneToOne
    private User trainer;

    @NotNull
    private LocalDate date;

    @NotNull
    private LocalTime time;
}
