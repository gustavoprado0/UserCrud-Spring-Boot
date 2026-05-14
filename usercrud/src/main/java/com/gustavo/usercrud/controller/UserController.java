package com.gustavo.usercrud.controller;

import com.gustavo.usercrud.model.User;
import com.gustavo.usercrud.repository.UserRepository;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository repository;
    private final BCryptPasswordEncoder encoder;

    public UserController(UserRepository repository, BCryptPasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @PostMapping
    public User create(@RequestBody User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return repository.save(user);
    }

    @GetMapping
    public List<User> list() {
        return repository.findAll();
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        User existing = repository.findById(id).orElse(null);

        if (existing != null) {
            existing.setName(user.getName());
            existing.setEmail(user.getEmail());
            existing.setAge(user.getAge());
            return repository.save(existing);
        }

        return null;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
