package com.hotel.controller;

import com.hotel.entity.Feedback;
import com.hotel.repository.FeedbackRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/feedback")
@CrossOrigin
public class FeedbackController {
    private final FeedbackRepository repo;
    public FeedbackController(FeedbackRepository repo) { this.repo=repo; }

    @GetMapping public List<Feedback> all() { return repo.findAll(); }
    @GetMapping("/{id}") public ResponseEntity<Feedback> one(@PathVariable Long id) {
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping public Feedback create(@RequestBody Feedback item) { return repo.save(item); }
    @PutMapping("/{id}") public ResponseEntity<Feedback> update(@PathVariable Long id, @RequestBody Feedback item) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        item.setId(id);
        return ResponseEntity.ok(repo.save(item));
    }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id); return ResponseEntity.noContent().build();
    }
}
