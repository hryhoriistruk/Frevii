package com.frevi.messaging.service;

import com.frevi.messaging.dto.request.ChatRequest;
import com.frevi.messaging.dto.response.ChatResponse;
import com.frevi.messaging.entity.Chat;
import com.frevi.messaging.mapper.ChatMapper;
import com.frevi.messaging.repository.jpa.ChatJpaRepository;
import com.frevi.messaging.repository.redis.ChatRedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatJpaRepository jpaRepository;
    private final ChatRedisRepository redisRepository;
    private final ChatMapper mapper;

    public ChatResponse save(ChatRequest request) {
        Chat chat = jpaRepository.save(mapper.toEntity(request));

        return mapper.toResponse(chat);
    }

    public void deleteChat(long id) {
        jpaRepository.deleteById(id);
    }
}
