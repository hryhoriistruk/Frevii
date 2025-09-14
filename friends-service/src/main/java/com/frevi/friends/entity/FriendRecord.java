package com.frevi.friends.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class FriendRecord {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "friend_record_seq")
    @SequenceGenerator(name = "friend_record_seq", sequenceName = "friend_record_sequence",
            allocationSize = 1)
    private Long id;

    private Long firstUserId;

    private Long secondUserId;
}
