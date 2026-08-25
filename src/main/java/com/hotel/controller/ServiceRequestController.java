package com.hotel.controller;

import com.hotel.entity.ServiceRequest;
import com.hotel.repository.ServiceRequestRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/services")
@CrossOrigin
public class ServiceRequestController {
    private final ServiceRequestRepository repo;
    public ServiceRequestController(ServiceRequestRepository repo) { this.repo=repo; }

    @GetMapping public List<ServiceRequest> all() { return repo.findAll(); }
    @GetMapping("/{id}") public ResponseEntity<ServiceRequest> one(@PathVariable Long id) {
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping public ServiceRequest create(@RequestBody ServiceRequest item) { return repo.save(item); }
    @PutMapping("/{id}") public ResponseEntity<ServiceRequest> update(@PathVariable Long id, @RequestBody ServiceRequest item) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        item.setId(id);
        return ResponseEntity.ok(repo.save(item));
    }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id); return ResponseEntity.noContent().build();
    }
}
