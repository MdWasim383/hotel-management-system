package com.hotel.controller;

import com.hotel.entity.Notification;
import com.hotel.repository.NotificationRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin
public class NotificationController {
    private final NotificationRepository repo;
    public NotificationController(NotificationRepository repo) { this.repo=repo; }

    @GetMapping public List<Notification> all() { return repo.findAll(); }
    @GetMapping("/{id}") public ResponseEntity<Notification> one(@PathVariable Long id) {
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping public Notification create(@RequestBody Notification item) { return repo.save(item); }
    @PutMapping("/{id}") public ResponseEntity<Notification> update(@PathVariable Long id, @RequestBody Notification item) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        item.setId(id);
        return ResponseEntity.ok(repo.save(item));
    }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id); return ResponseEntity.noContent().build();
    }
}
