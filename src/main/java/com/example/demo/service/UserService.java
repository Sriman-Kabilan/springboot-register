package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    // 📝 REGISTER
    public String registerUser(User user) {

        if (repository.existsByEmail(user.getEmail())) {
            return "Email already exists";
        }

        repository.save(user);
        return "User registered successfully";
    }

    // 🔐 LOGIN
    public boolean loginUser(String email, String password) {

        Optional<User> user = repository.findByEmail(email);

        if (user.isEmpty()) {
            return false;
        }

        return user.get().getPassword().equals(password);
    }
    
    public List<User> getAllUsers() {
        return repository.findAll();
    }

}
