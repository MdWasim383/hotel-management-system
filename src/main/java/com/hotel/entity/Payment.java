package com.hotel.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity @Table(name="payments") @Getter @Setter @NoArgsConstructor
public class Payment {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 private Long reservationId; private Long guestId; private BigDecimal amount;
 private String paymentMethod; private String transactionReference;
 private String status; private LocalDateTime paidAt;
}
