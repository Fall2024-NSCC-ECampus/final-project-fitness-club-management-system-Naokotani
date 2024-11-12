package com.example.fitnessclub.repository;

import com.example.fitnessclub.model.User;
import com.example.fitnessclub.model.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.roles WHERE u.email = ?1")
    Optional<User> findByEmailWithRoles(String email);

    @Query("SELECT u FROM User u JOIN FETCH u.roles r WHERE r.role = ?1")
    Optional<List<User>> findUsersByRole(UserRoles role);

    Optional<User> findByEmail(String email);
}
