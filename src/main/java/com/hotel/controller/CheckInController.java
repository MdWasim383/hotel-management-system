package com.hotel.controller;

import com.hotel.entity.CheckIn;
import com.hotel.repository.CheckInRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/check-ins")
@CrossOrigin
public class CheckInController {
    private final CheckInRepository repo;
    public CheckInController(CheckInRepository repo) { this.repo=repo; }

    @GetMapping public List<CheckIn> all() { return repo.findAll(); }
    @GetMapping("/{id}") public ResponseEntity<CheckIn> one(@PathVariable Long id) {
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping public CheckIn create(@RequestBody CheckIn item) { return repo.save(item); }
    @PutMapping("/{id}") public ResponseEntity<CheckIn> update(@PathVariable Long id, @RequestBody CheckIn item) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        item.setId(id);
        return ResponseEntity.ok(repo.save(item));
    }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id); return ResponseEntity.noContent().build();
    }
}
