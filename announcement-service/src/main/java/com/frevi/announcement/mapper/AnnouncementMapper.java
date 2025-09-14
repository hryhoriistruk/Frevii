package com.frevi.announcement.mapper;

import com.frevi.announcement.dto.AnnouncementRequest;
import com.frevi.announcement.dto.AnnouncementResponse;
import com.frevi.announcement.entity.Announcement;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AnnouncementMapper {
    Announcement fromRequest(AnnouncementRequest announcementRequest);

    AnnouncementResponse toResponse(Announcement announcement);
}
