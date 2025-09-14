package com.frevi.messaging.mapper;

import com.frevi.messaging.dto.response.PreKeyResponse;
import com.frevi.messaging.entity.PreKey;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PreKeyMapper {
    PreKeyResponse toResponse(PreKey preKey);
}
