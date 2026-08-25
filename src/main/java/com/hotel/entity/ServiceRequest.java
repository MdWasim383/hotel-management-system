package com.hotel.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity @Table(name="service_requests") @Getter @Setter @NoArgsConstructor
public class ServiceRequest {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 private Long guestId; private Long roomId; private String serviceType;
 private String description; private String status; private LocalDateTime requestedAt;
}
