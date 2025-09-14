package com.frevi.messaging.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

import java.util.List;

@Entity
@Getter
@Setter
@RedisHash("chat")
public class Chat {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chat_seq")
    @SequenceGenerator(name = "chat_seq", sequenceName = "chat_sequence", allocationSize = 1)
    private Long id;

    private String firstUserId;

    private String secondUserId;

    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Message> messages;
}
