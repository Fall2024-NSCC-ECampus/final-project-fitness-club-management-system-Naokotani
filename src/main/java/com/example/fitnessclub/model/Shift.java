package com.example.fitnessclub.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Shift {
    @Id
    private int id;

    @NotNull
    @OneToOne
    private ClassDetails classDetails;

    @NotNull
    @OneToOne
    private ClassDate classDate;

    @NotNull
    @OneToOne
    private User trainer;

    public Shift(ClassDetails classDetails, User user, ClassDate classDate) {
        this.classDetails = classDetails;
        this.trainer = user;
        this.classDate = classDate;
    }
}
