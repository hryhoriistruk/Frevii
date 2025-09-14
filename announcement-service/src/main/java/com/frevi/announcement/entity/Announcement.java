package com.frevi.announcement.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class Announcement {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "announcement_seq")
    @SequenceGenerator(name = "announcement_seq", sequenceName = "announcement_sequence",
                        allocationSize = 1)
    @Setter(AccessLevel.NONE)
    private Long id;

    private String title;

    private String content;

    private LocalDateTime createdAt;

    private String creatorId;
}
