package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*") // Allow Flutter
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    // ✅ REGISTER
    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody User user) {

        Map<String, String> response = new HashMap<>();
        String result = userService.registerUser(user);

        if ("Email already exists".equals(result)) {
            response.put("message", result);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        response.put("message", "User registered successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // ✅ LOGIN (JWT generation only)
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> request) {

        String email = request.get("username"); // Flutter sends "username"
        String password = request.get("password");

        Map<String,String> response = new HashMap<>();

        boolean isValid = userService.loginUser(email, password);

        if (!isValid) {
            response.put("error", "Invalid credentials");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
        
        if (email == null || password == null) {
            response.put("error", "Email or password missing");
            return ResponseEntity.badRequest().body(response);
        }


        String token = jwtUtil.generateToken(email);
        response.put("token", token);
        response.put("message", "Login successful");

        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

}
