package com.hotel.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.Map;

@Getter @AllArgsConstructor
public class DashboardStats {
    private long rooms;
    private long availableRooms;
    private long occupiedRooms;
    private long reservations;
    private long guests;
    private long employees;
    private long feedback;
    private long pendingServices;
    private double revenue;
    private Map<String, Long> roomStatus;
}
