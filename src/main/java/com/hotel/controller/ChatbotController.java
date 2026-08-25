package com.hotel.controller;

import com.hotel.dto.ChatRequest;
import com.hotel.dto.ChatResponse;
import com.hotel.service.ChatbotService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chatbot")
@CrossOrigin
public class ChatbotController {
    private final ChatbotService service;
    public ChatbotController(ChatbotService service) { this.service=service; }

    @PostMapping
    public ChatResponse chat(@RequestBody ChatRequest request) {
        return service.reply(request.getMessage());
    }
}
