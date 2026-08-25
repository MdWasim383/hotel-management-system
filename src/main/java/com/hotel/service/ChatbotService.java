package com.hotel.service;

import com.hotel.dto.ChatResponse;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ChatbotService {
    public ChatResponse reply(String message) {
        String m = message == null ? "" : message.toLowerCase();
        if (m.contains("room") && (m.contains("available") || m.contains("vacant")))
            return new ChatResponse("You can check all currently available rooms from Room Management.",
                    List.of("View Available Rooms","Book Room"));
        if (m.contains("book"))
            return new ChatResponse("I can guide you through booking: choose dates, select a room, enter guest details and complete payment.",
                    List.of("Start Booking","View Rooms"));
        if (m.contains("check-in") || m.contains("checkin"))
            return new ChatResponse("Open Reservations, select the booking, verify the guest and confirm check-in.",
                    List.of("Open Reservations","Check-In"));
        if (m.contains("check-out") || m.contains("checkout"))
            return new ChatResponse("Open Check-Out, select the guest, review the bill and complete payment.",
                    List.of("Check-Out","Payments"));
        if (m.contains("feedback"))
            return new ChatResponse("Please open Feedback and submit a rating and comments about your stay.",
                    List.of("Give Feedback"));
        if (m.contains("restaurant") || m.contains("food"))
            return new ChatResponse("Food orders can be created from Food & Restaurant Management.",
                    List.of("Food Orders"));
        if (m.contains("service"))
            return new ChatResponse("You can request housekeeping, laundry, room service, parking or maintenance.",
                    List.of("Hotel Services","Housekeeping"));
        return new ChatResponse("I can help with rooms, bookings, check-in, check-out, payments, food, services and feedback.",
                List.of("Rooms","Bookings","Check-In","Check-Out","Feedback"));
    }
}
