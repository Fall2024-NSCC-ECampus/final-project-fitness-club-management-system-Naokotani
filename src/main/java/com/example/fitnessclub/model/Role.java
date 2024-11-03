package com.example.fitnessclub.model;

import jakarta.persistence.*;
import lombok.Setter;

@Entity
@IdClass(RoleId.class)
@Table(name="roles")
public class Role {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    private User userId;

    @Setter
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