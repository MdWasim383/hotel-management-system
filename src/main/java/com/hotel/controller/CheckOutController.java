package com.hotel.controller;

import com.hotel.entity.CheckOut;
import com.hotel.repository.CheckOutRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/check-outs")
@CrossOrigin
public class CheckOutController {
    private final CheckOutRepository repo;
    public CheckOutController(CheckOutRepository repo) { this.repo=repo; }

    @GetMapping public List<CheckOut> all() { return repo.findAll(); }
    @GetMapping("/{id}") public ResponseEntity<CheckOut> one(@PathVariable Long id) {
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping public CheckOut create(@RequestBody CheckOut item) { return repo.save(item); }
    @PutMapping("/{id}") public ResponseEntity<CheckOut> update(@PathVariable Long id, @RequestBody CheckOut item) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        item.setId(id);
        return ResponseEntity.ok(repo.save(item));
    }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id); return ResponseEntity.noContent().build();
    }
}
