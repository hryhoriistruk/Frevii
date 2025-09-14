package com.frevi.messaging.mapper;

import com.frevi.messaging.dto.request.ChatRequest;
import com.frevi.messaging.dto.response.ChatResponse;
import com.frevi.messaging.entity.Chat;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChatMapper {
    Chat toEntity(ChatRequest chatRequest);

    ChatResponse toResponse(Chat chat);
}
