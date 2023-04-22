package com.example.practice2.thymeleaf.respository;

import com.example.practice2.thymeleaf.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserById(Long userId);
}
