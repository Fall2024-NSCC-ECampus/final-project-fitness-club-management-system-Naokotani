package com.example.fitnessclub.model;

import java.io.Serializable;
import java.util.Objects;

public class RoleId implements Serializable {
    private Long userId;
    private UserRoles role;

    // Default constructor, equals, and hashCode
    public RoleId() {}

    public RoleId(Long userId, UserRoles role) {
        this.userId = userId;
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoleId)) return false;
        RoleId that = (RoleId) o;
        return Objects.equals(userId, that.userId) && role == that.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, role);
    }
}
