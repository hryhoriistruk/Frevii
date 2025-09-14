package com.frevi.messaging.dto.response;

public record MessageResponse(Long id,
                              String fromUser,
                              String toUser,
                              String ephemeralPubBase64,
                              String ciphertextBase64,
                              String nonceBase64) {
}
