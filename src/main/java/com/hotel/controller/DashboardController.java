package com.hotel.controller;

import com.hotel.dto.DashboardStats;
import com.hotel.service.DashboardService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin
public class DashboardController {
    private final DashboardService service;
    public DashboardController(DashboardService service) { this.service=service; }

    @GetMapping
    public DashboardStats stats() { return service.getStats(); }
}
