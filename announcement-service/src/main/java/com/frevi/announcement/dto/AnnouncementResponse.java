package com.frevi.announcement.dto;

import java.time.LocalDateTime;

public record AnnouncementResponse(Long id,
                                   String title,
                                   String content,
                                   LocalDateTime createdAt,
                                   String creatorId) {
}
