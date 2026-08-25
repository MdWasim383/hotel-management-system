package com.hotel.controller;

import com.hotel.entity.Guest;
import com.hotel.repository.GuestRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/guests")
@CrossOrigin
public class GuestController {
    private final GuestRepository repo;
    public GuestController(GuestRepository repo) { this.repo=repo; }

    @GetMapping public List<Guest> all() { return repo.findAll(); }
    @GetMapping("/{id}") public ResponseEntity<Guest> one(@PathVariable Long id) {
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping public Guest create(@RequestBody Guest item) { return repo.save(item); }
    @PutMapping("/{id}") public ResponseEntity<Guest> update(@PathVariable Long id, @RequestBody Guest item) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        item.setId(id);
        return ResponseEntity.ok(repo.save(item));
    }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id); return ResponseEntity.noContent().build();
    }
}
