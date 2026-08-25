package com.hotel.controller;

import com.hotel.entity.MaintenanceRequest;
import com.hotel.repository.MaintenanceRequestRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/maintenance")
@CrossOrigin
public class MaintenanceRequestController {
    private final MaintenanceRequestRepository repo;
    public MaintenanceRequestController(MaintenanceRequestRepository repo) { this.repo=repo; }

    @GetMapping public List<MaintenanceRequest> all() { return repo.findAll(); }
    @GetMapping("/{id}") public ResponseEntity<MaintenanceRequest> one(@PathVariable Long id) {
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping public MaintenanceRequest create(@RequestBody MaintenanceRequest item) { return repo.save(item); }
    @PutMapping("/{id}") public ResponseEntity<MaintenanceRequest> update(@PathVariable Long id, @RequestBody MaintenanceRequest item) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        item.setId(id);
        return ResponseEntity.ok(repo.save(item));
    }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id); return ResponseEntity.noContent().build();
    }
}
