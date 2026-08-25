package com.hotel.controller;

import com.hotel.entity.Room;
import com.hotel.repository.RoomRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@CrossOrigin
public class RoomController {
    private final RoomRepository repo;
    public RoomController(RoomRepository repo) { this.repo=repo; }

    @GetMapping public List<Room> all() { return repo.findAll(); }
    @GetMapping("/{id}") public ResponseEntity<Room> one(@PathVariable Long id) {
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping public Room create(@RequestBody Room item) { return repo.save(item); }
    @PutMapping("/{id}") public ResponseEntity<Room> update(@PathVariable Long id, @RequestBody Room item) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        item.setId(id);
        return ResponseEntity.ok(repo.save(item));
    }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id); return ResponseEntity.noContent().build();
    }
}
