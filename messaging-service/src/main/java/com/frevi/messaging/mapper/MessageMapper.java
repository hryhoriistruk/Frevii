package com.frevi.messaging.mapper;

import com.frevi.messaging.dto.request.MessageRequest;
import com.frevi.messaging.dto.response.MessageResponse;
import com.frevi.messaging.entity.Message;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MessageMapper {
    Message toEntity(MessageRequest messageRequest);

    MessageResponse toResponse(Message message);
}
