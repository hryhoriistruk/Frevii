package com.frevi.announcement.dto;

import java.time.LocalDateTime;

public record AnnouncementRequest(String title,
                                  String content,
                                  LocalDateTime createdAt,
                                  String creatorId) {
}
