package com.frevi.messaging.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Getter
@Setter
public class PreKey {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chat_seq")
    @SequenceGenerator(name = "chat_seq", sequenceName = "chat_sequence", allocationSize = 1)
    private Long id;

    private String userId;

    private int deviceId;

    @Lob
    private byte[] prekeyPub;

    private boolean used = false;

    private Instant createdAt = Instant.now();
}
