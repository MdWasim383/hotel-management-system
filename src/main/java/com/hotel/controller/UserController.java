package com.hotel.controller;

import com.hotel.entity.User;
import com.hotel.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {
    private final UserRepository repo;
    public UserController(UserRepository repo) { this.repo=repo; }

    @GetMapping public List<User> all() { return repo.findAll(); }
    @GetMapping("/{id}") public ResponseEntity<User> one(@PathVariable Long id) {
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping public User create(@RequestBody User item) { return repo.save(item); }
    @PutMapping("/{id}") public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User item) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        item.setId(id);
        return ResponseEntity.ok(repo.save(item));
    }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id); return ResponseEntity.noContent().build();
    }
}
