package com.example.myspringbootapp.repository;

import com.example.myspringbootapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String userName);
    Optional<User> findByEmail(String userEmail);
}
