package com.frevi.friends.dto;

import jakarta.validation.constraints.Positive;

public record FriendRequest(@Positive Long firstUserId,
                            @Positive Long secondUserId) {
}
