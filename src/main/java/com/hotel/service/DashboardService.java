package com.hotel.service;

import com.hotel.dto.DashboardStats;
import com.hotel.repository.*;
import org.springframework.stereotype.Service;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class DashboardService {
    private final RoomRepository rooms;
    private final ReservationRepository reservations;
    private final GuestRepository guests;
    private final EmployeeRepository employees;
    private final FeedbackRepository feedback;
    private final ServiceRequestRepository services;
    private final PaymentRepository payments;

    public DashboardService(RoomRepository rooms, ReservationRepository reservations,
                            GuestRepository guests, EmployeeRepository employees,
                            FeedbackRepository feedback, ServiceRequestRepository services,
                            PaymentRepository payments) {
        this.rooms=rooms; this.reservations=reservations; this.guests=guests;
        this.employees=employees; this.feedback=feedback; this.services=services; this.payments=payments;
    }

    public DashboardStats getStats() {
        Map<String, Long> status = new LinkedHashMap<>();
        rooms.findAll().forEach(r -> status.put(r.getStatus(), status.getOrDefault(r.getStatus(),0L)+1));
        double revenue = payments.findAll().stream()
                .filter(p -> "PAID".equalsIgnoreCase(p.getStatus()) && p.getAmount()!=null)
                .mapToDouble(p -> p.getAmount().doubleValue()).sum();
        long available = rooms.findAll().stream().filter(r -> "AVAILABLE".equalsIgnoreCase(r.getStatus())).count();
        long occupied = rooms.findAll().stream().filter(r -> "OCCUPIED".equalsIgnoreCase(r.getStatus())).count();
        long pending = services.findAll().stream().filter(s -> !"COMPLETED".equalsIgnoreCase(s.getStatus())).count();
        return new DashboardStats(rooms.count(), available, occupied, reservations.count(), guests.count(),
                employees.count(), feedback.count(), pending, revenue, status);
    }
}
