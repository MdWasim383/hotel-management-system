package com.hotel.controller;

import com.hotel.entity.FoodOrder;
import com.hotel.repository.FoodOrderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/food-orders")
@CrossOrigin
public class FoodOrderController {
    private final FoodOrderRepository repo;
    public FoodOrderController(FoodOrderRepository repo) { this.repo=repo; }

    @GetMapping public List<FoodOrder> all() { return repo.findAll(); }
    @GetMapping("/{id}") public ResponseEntity<FoodOrder> one(@PathVariable Long id) {
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping public FoodOrder create(@RequestBody FoodOrder item) { return repo.save(item); }
    @PutMapping("/{id}") public ResponseEntity<FoodOrder> update(@PathVariable Long id, @RequestBody FoodOrder item) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        item.setId(id);
        return ResponseEntity.ok(repo.save(item));
    }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id); return ResponseEntity.noContent().build();
    }
}
