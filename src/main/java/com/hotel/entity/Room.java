package com.hotel.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity @Table(name="rooms") @Getter @Setter @NoArgsConstructor
public class Room {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(unique=true, nullable=false)
 private String roomNumber;
 private String roomType;
 private BigDecimal price;
 private Integer capacity;
 private String status;
 private String floor;
 private String facilities;
}
