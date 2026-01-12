package com.example.demo.repo;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    // 🔐 Used for Flutter login
    Optional<User> findByEmail(String email);

    // ✅ Optional (recommended): prevent duplicate registration
    boolean existsByEmail(String email);
}
