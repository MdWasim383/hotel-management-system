package com.hotel.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity @Table(name="check_outs") @Getter @Setter @NoArgsConstructor
public class CheckOut {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 private Long reservationId; private Long guestId; private Long roomId;
 private LocalDateTime checkOutTime; private BigDecimal finalAmount; private String status;
}
