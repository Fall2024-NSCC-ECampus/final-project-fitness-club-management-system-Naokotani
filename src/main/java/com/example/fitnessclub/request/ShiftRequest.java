package com.example.fitnessclub.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

@Data
public class ShiftRequest {
    @NotNull(message="Must select class")
    private long classId;
    @Pattern(regexp="^\\d{4}-\\d{2}-\\d{2}$", message="Must be in the format YYYY-MM-DD")
    private String dateString;
    @Pattern(regexp="^([01]?[0-9]|2[0-3]):([0-5]?[0-9])$", message="Must be the 24h format HH:MM")
    private String startTimeString;
    @Pattern(regexp="^([01]?[0-9]|2[0-3]):([0-5]?[0-9])$", message="Must be the 24h format HH:MM")
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
