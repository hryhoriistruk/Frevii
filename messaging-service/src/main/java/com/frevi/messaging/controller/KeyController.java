package com.frevi.messaging.controller;

import com.frevi.messaging.dto.request.KeyBundle;
import com.frevi.messaging.dto.response.PreKeyResponse;
import com.frevi.messaging.service.KeyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/key")
@RequiredArgsConstructor
public class KeyController {
    private final KeyService keyService;

    @PostMapping
    public ResponseEntity<String> register(@RequestBody KeyBundle bundle) {
        keyService.registerPreKeys(bundle.userId, bundle.deviceId, bundle.preKeysBase64);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<PreKeyResponse> fetch(@RequestParam String userId,
                                                @RequestParam int deviceId) {
        PreKeyResponse response = keyService.consumePreKeys(userId, deviceId);

        if (response == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(response);
    }
}
