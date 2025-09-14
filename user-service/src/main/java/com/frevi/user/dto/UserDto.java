package com.frevi.user.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class UserDto{
    private final Long id;
    private final String name;
    private final String surname;
    private final String email;
    private final String phoneNumber;
    private final List<String> deviceTokens;
    private final Integer age;

    @Setter
    private String avatarUrl;
}
