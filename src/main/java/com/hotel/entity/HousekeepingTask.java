package com.hotel.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity @Table(name="housekeeping_tasks") @Getter @Setter @NoArgsConstructor
public class HousekeepingTask {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 private Long roomId; private String assignedTo; private String taskType;
 private String status; private LocalDateTime assignedAt; private LocalDateTime completedAt;
}
