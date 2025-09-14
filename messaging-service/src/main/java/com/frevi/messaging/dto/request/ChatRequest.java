package com.frevi.messaging.dto.request;

public record ChatRequest(String firstUserId,
                          String secondUserId) {
}
