package com.example.fitnessclub.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ShiftRequest {
    private long classId;
    private long trainerId;
    private String dateString;
    private String startTimeString;
    private String endTimeString;

    public LocalDate parseDate() throws DateTimeParseException {
        return LocalDate.parse(dateString);
    }

    public LocalTime parseStartTime() throws DateTimeParseException {
        return LocalTime.parse(startTimeString);
    }

    public LocalTime parseEndTime() throws DateTimeParseException {
        return LocalTime.parse(endTimeString);
    }
}
