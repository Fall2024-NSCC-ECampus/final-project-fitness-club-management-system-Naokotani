package com.example.fitnessclub.model;

import jakarta.persistence.*;

@Entity
@IdClass(RoleId.class)
@Table(name="roles")
public class Role {

    @Id
    @ManyToOne
    @JoinColumn(name="user_id")
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

    public void setRole(UserRoles role) {
        this.role = role;
    }
}
