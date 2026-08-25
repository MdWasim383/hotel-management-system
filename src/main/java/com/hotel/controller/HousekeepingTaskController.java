package com.hotel.controller;

import com.hotel.entity.HousekeepingTask;
import com.hotel.repository.HousekeepingTaskRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/housekeeping")
@CrossOrigin
public class HousekeepingTaskController {
    private final HousekeepingTaskRepository repo;
    public HousekeepingTaskController(HousekeepingTaskRepository repo) { this.repo=repo; }

    @GetMapping public List<HousekeepingTask> all() { return repo.findAll(); }
    @GetMapping("/{id}") public ResponseEntity<HousekeepingTask> one(@PathVariable Long id) {
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping public HousekeepingTask create(@RequestBody HousekeepingTask item) { return repo.save(item); }
    @PutMapping("/{id}") public ResponseEntity<HousekeepingTask> update(@PathVariable Long id, @RequestBody HousekeepingTask item) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        item.setId(id);
        return ResponseEntity.ok(repo.save(item));
    }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id); return ResponseEntity.noContent().build();
    }
}
