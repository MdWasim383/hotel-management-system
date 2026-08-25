package com.hotel.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity @Table(name="guests") @Getter @Setter @NoArgsConstructor
public class Guest {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 private String fullName;
 private String email;
 private String phone;
 private String idProof;
 private String address;
 private LocalDate dateOfBirth;
}
