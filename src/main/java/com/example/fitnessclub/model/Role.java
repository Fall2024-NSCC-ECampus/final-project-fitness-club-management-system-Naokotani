package com.example.fitnessclub.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@IdClass(RoleId.class)
@Table(name="roles")
public class Role {

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    private User userId;

    @Id
    @Column(name="role")
    @Enumerated(EnumType.STRING)
    private UserRoles role;

    public Role() {}

    public Role(User user, UserRoles role) {
        this.userId = user;
        this.role = role;
    }

    public void setUser(User user) {
        this.userId = user;
    }

}