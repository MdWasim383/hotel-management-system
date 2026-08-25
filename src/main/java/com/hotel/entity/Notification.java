package com.hotel.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity @Table(name="notifications") @Getter @Setter @NoArgsConstructor
public class Notification {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 private String title; @Column(length=1000) private String message;
 private String type; private Boolean isRead; private LocalDateTime createdAt;
}
