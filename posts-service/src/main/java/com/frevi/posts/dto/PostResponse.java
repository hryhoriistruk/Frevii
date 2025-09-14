package com.frevi.posts.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Getter
public class PostResponse {
    private final Long id;
    private final String name;
    private final String surname;
    private final String email;
    private final String phoneNumber;
    private final List<String> deviceTokens;
    private final Integer age;
    private final Long userId;
    private final List<String> mediaUrls = new ArrayList<>();
}
