package com.hotel.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity @Table(name="maintenance_requests") @Getter @Setter @NoArgsConstructor
public class MaintenanceRequest {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 private Long roomId; private String issue; private String priority;
 private String status; private String assignedTo; private LocalDateTime createdAt;
}
