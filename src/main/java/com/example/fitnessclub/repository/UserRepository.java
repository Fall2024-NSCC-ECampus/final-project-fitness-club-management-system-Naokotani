package com.example.fitnessclub.repository;

import com.example.fitnessclub.model.Role;
import com.example.fitnessclub.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where u.email = ?1")
    Optional<User> emailExists(String emailAddress);

    @Query("SELECT u FROM User u JOIN FETCH u.roles r WHERE r.role = 'ADMIN'")
    Optional<List<User>> findAdmins();

    User findByEmail(String email);
}
