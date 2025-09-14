package com.frevi.messaging.controller;

import com.frevi.messaging.dto.request.MessageRequest;
import com.frevi.messaging.dto.response.MessageResponse;
import com.frevi.messaging.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/messages")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;

    @PostMapping
    public ResponseEntity<MessageResponse> sendMessage(@RequestBody MessageRequest messageRequest) {
        MessageResponse response = messageService.save(messageRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .build()
                .toUri();

        return ResponseEntity.created(location).body(response);
    }

    @GetMapping("{chatId}")
    public ResponseEntity<PagedModel<EntityModel<MessageResponse>>> getMessagesFromChat(@PathVariable long chatId,
                                                                                        Pageable pageable,
                                                                                        PagedResourcesAssembler<MessageResponse> assembler) {
        Page<MessageResponse> page = messageService.findAllFromChat(pageable, chatId);

        return ResponseEntity.ok(assembler.toModel(page));
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<MessageResponse> viewMessage(@PathVariable long id) {
        MessageResponse response = messageService.viewMessage(id);

        return ResponseEntity.ok(response);
    }
}
