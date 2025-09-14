package com.frevi.user.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record UserRequest(@NotBlank String name,
                          @NotBlank String surname,
                          @NotBlank String email,
                          @NotBlank String phoneNumber,
                          @NotNull List<String> deviceTokens,
                          @Positive Integer age,
                          @NotNull MultipartFile avatar) {
}
