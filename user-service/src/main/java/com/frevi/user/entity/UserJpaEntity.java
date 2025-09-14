package com.frevi.user.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class UserJpaEntity {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(generator = "user_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "user_seq", sequenceName = "user_sequence", allocationSize = 1)
    private long id;

    private String name;

    private String surname;

    private String email;

    private String phoneNumber;

    @ElementCollection
    private List<String> deviceTokens = new ArrayList<>();

    private int age;

    private String avatarFileName;
}
