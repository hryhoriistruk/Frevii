package com.frevi.messaging.service;

import com.frevi.messaging.dto.response.PreKeyResponse;
import com.frevi.messaging.entity.PreKey;
import com.frevi.messaging.mapper.PreKeyMapper;
import com.frevi.messaging.repository.jpa.PreKeyRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.KeyException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KeyService {
    private final PreKeyRepository preKeyRepository;
    private final PreKeyMapper mapper;

    public void registerPreKeys(String userId, int deviceId, List<String> preKeysBase64) {
        preKeysBase64.forEach(preKeyBase64 -> {
            PreKey preKey = new PreKey();
            preKey.setUserId(userId);
            preKey.setDeviceId(deviceId);
            preKeyRepository.save(preKey);

        });
    }

    public PreKeyResponse consumePreKeys(String userId, int deviceId) throws KeyException {
        PreKey preKey = preKeyRepository.consumePreKey(userId, deviceId)
                .orElseThrow(KeyException::new);

        return mapper.toResponse(preKey);
    }
}
