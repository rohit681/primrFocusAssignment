package com.example.demo.controller;

import com.example.demo.DTO.User;
import com.example.demo.stores.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        return ResponseEntity.ok(userRepository.saveUser(user));
    }

    @PutMapping("/{username}")
    public ResponseEntity<User> updateUser(@PathVariable String username, @RequestParam String name) {
        return ResponseEntity.ok(userRepository.updateNameByUsername(username, name));
    }

    @GetMapping
    public ResponseEntity<List<User>> listUsers(@RequestParam(defaultValue = "asc") String order) {
        return ResponseEntity.ok(userRepository.getUsersSortedByDob(order));
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Void> deleteUser(@PathVariable String username) {
        userRepository.deleteByUsername(username);
        return ResponseEntity.noContent().build();
    }
}
