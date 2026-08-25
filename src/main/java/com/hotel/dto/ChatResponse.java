package com.hotel.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.List;
@Getter @AllArgsConstructor
public class ChatResponse { private String reply; private List<String> actions; }
