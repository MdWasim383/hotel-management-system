package com.hotel.controller;

import com.hotel.entity.Payment;
import com.hotel.repository.PaymentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin
public class PaymentController {
    private final PaymentRepository repo;
    public PaymentController(PaymentRepository repo) { this.repo=repo; }

    @GetMapping public List<Payment> all() { return repo.findAll(); }
    @GetMapping("/{id}") public ResponseEntity<Payment> one(@PathVariable Long id) {
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping public Payment create(@RequestBody Payment item) { return repo.save(item); }
    @PutMapping("/{id}") public ResponseEntity<Payment> update(@PathVariable Long id, @RequestBody Payment item) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        item.setId(id);
        return ResponseEntity.ok(repo.save(item));
    }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id); return ResponseEntity.noContent().build();
    }
}
