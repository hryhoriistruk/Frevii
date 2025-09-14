package com.frevi.messaging.dto.request;

public record MessageRequest(String fromUser,
                             String toUser,
                             String ephemeralPubBase64,
                             String ciphertextBase64,
                             String nonceBase64) {
}
