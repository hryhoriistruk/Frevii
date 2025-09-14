package com.frevi.messaging.controller;

import com.frevi.messaging.dto.request.ChatRequest;
import com.frevi.messaging.dto.response.ChatResponse;
import com.frevi.messaging.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/chat")
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

    @PostMapping
    public ResponseEntity<ChatResponse> createChat(@RequestBody ChatRequest chatRequest) {
        ChatResponse chatResponse = chatService.save(chatRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .build()
                .toUri();

        return ResponseEntity.created(location).body(chatResponse);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteChat(@PathVariable long id) {
        chatService.deleteChat(id);

        return ResponseEntity.noContent().build();
    }
}
