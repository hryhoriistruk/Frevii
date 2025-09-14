package com.frevi.messaging.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

import java.time.Instant;

@Entity
@Getter
@Setter
@RedisHash("message")
public class Message {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chat_seq")
    @SequenceGenerator(name = "chat_seq", sequenceName = "chat_sequence", allocationSize = 1)
    private Long id;

    private String fromUser;

    private String toUser;

    @Lob private byte[] ephemeralPub;

    @Lob private byte[] ciphertext;

    @Lob private byte[] nonce;

    private boolean delivered = false;

    private Instant createdAt = Instant.now();

    @ManyToOne
    @JoinColumn(name = "chat_id")
    private Chat chat;
}
