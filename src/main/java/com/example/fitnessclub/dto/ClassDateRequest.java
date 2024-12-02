package com.example.fitnessclub.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ClassDateRequest {
    @NotNull
    @FutureOrPresent(message = "Date must be in the future.")
    private LocalDate date;
    @NotNull
    private LocalTime startTime;
    @NotNull
    private LocalTime endTime;

    @AssertTrue(message = "Start time must be before end time")
    public boolean isStartBeforeEnd() {
        return startTime.isBefore(endTime);
    }

}
