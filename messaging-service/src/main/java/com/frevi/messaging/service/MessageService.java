package com.frevi.messaging.service;

import com.frevi.messaging.dto.request.MessageRequest;
import com.frevi.messaging.dto.response.MessageResponse;
import com.frevi.messaging.entity.Message;
import com.frevi.messaging.mapper.MessageMapper;
import com.frevi.messaging.repository.jpa.MessageJpaRepository;
import com.frevi.messaging.repository.redis.MessageRedisRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageJpaRepository jpaRepository;
    private final MessageRedisRepository redisRepository;
    private final KeyService keyService;
    private final MessageMapper mapper;

    public Message save(Message message) {
        redisRepository.delete(message);

        return jpaRepository.save(message);
    }

    public MessageResponse save(MessageRequest messageRequest) {
        Message message = save(mapper.toEntity(messageRequest));

        redisRepository.delete(message);

        return mapper.toResponse(message);
    }

    public Page<MessageResponse> findAllFromChat(Pageable pageable, long chatId) {
        long sqlCount = jpaRepository.countByChatId(chatId);
        long redisCount = redisRepository.countByChatId(chatId);

        if (redisCount == sqlCount) {
            return redisRepository.findByChatId(chatId, pageable)
                    .map(mapper::toResponse);
        }

        Page<Message> messages = jpaRepository.findByChatId(chatId, pageable);

        messages.forEach(m -> {
            if (!redisRepository.existsById(m.getId())) {
                redisRepository.save(m);
            }
        });

        return messages.map(mapper::toResponse);
    }

    public MessageResponse viewMessage(long id) throws EntityNotFoundException {
        Message message = jpaRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        message = save(message);

        return mapper.toResponse(message);
    }
}
