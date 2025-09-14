package com.frevi.friends.mapper;

import com.frevi.friends.dto.FriendRequest;
import com.frevi.friends.dto.FriendResponse;
import com.frevi.friends.entity.FriendRecord;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FriendMapper {
    FriendRecord fromRequest(FriendRequest friendRequest);

    FriendResponse toResponse(FriendRecord friendRecord);
}
