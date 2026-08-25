package com.hotel.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity @Table(name="reservations") @Getter @Setter @NoArgsConstructor
public class Reservation {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 private String bookingCode; private Long guestId; private Long roomId;
 private LocalDate checkIn; private LocalDate checkOut;
 private Integer guests; private BigDecimal totalAmount; private String status;
}
