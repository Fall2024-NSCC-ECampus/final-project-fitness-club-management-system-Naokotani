package com.example.fitnessclub.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Shift {
    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @ManyToOne
    private ClassDetails classDetails;

    @NotNull
    @OneToOne
    private ClassDate classDate;

    @NotNull
    @ManyToOne
    private User trainer;

    @NotNull
    private boolean isAttendance;

    public Shift(ClassDetails classDetails, User user, ClassDate classDate) {
        this.classDetails = classDetails;
        this.trainer = user;
        this.classDate = classDate;
        this.isAttendance = false;
    }

    public Shift(ClassDetails classDetails, User trainer, ClassDate classDate, boolean isAttendance) {
        this.classDetails = classDetails;
        this.classDate = classDate;
        this.trainer = trainer;
        this.isAttendance = isAttendance;
    }
}
