package com.hotel.controller;

import com.hotel.entity.Employee;
import com.hotel.repository.EmployeeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin
public class EmployeeController {
    private final EmployeeRepository repo;
    public EmployeeController(EmployeeRepository repo) { this.repo=repo; }

    @GetMapping public List<Employee> all() { return repo.findAll(); }
    @GetMapping("/{id}") public ResponseEntity<Employee> one(@PathVariable Long id) {
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping public Employee create(@RequestBody Employee item) { return repo.save(item); }
    @PutMapping("/{id}") public ResponseEntity<Employee> update(@PathVariable Long id, @RequestBody Employee item) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        item.setId(id);
        return ResponseEntity.ok(repo.save(item));
    }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id); return ResponseEntity.noContent().build();
    }
}
