package com.hotel.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity @Table(name="food_orders") @Getter @Setter @NoArgsConstructor
public class FoodOrder {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 private Long guestId; private Long roomId; private String itemName;
 private Integer quantity; private BigDecimal amount; private String status;
 private LocalDateTime orderedAt;
}
