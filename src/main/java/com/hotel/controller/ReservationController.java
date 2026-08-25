package com.hotel.controller;

import com.hotel.entity.Reservation;
import com.hotel.repository.ReservationRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@CrossOrigin
public class ReservationController {
    private final ReservationRepository repo;
    public ReservationController(ReservationRepository repo) { this.repo=repo; }

    @GetMapping public List<Reservation> all() { return repo.findAll(); }
    @GetMapping("/{id}") public ResponseEntity<Reservation> one(@PathVariable Long id) {
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping public Reservation create(@RequestBody Reservation item) { return repo.save(item); }
    @PutMapping("/{id}") public ResponseEntity<Reservation> update(@PathVariable Long id, @RequestBody Reservation item) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        item.setId(id);
        return ResponseEntity.ok(repo.save(item));
    }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id); return ResponseEntity.noContent().build();
    }
}
