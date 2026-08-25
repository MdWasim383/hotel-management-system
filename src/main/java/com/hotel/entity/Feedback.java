package com.hotel.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity @Table(name="feedback") @Getter @Setter @NoArgsConstructor
public class Feedback {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 private Long guestId; private Long reservationId; private Integer rating;
 private Integer roomRating; private Integer foodRating; private Integer staffRating;
 private Integer cleanlinessRating; @Column(length=2000) private String comments;
 private LocalDateTime submittedAt;
}
