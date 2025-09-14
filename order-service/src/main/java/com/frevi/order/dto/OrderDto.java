package com.frevi.order.dto;

import com.frevi.order.entity.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record OrderDto(@Positive Long id,
                       @NotNull BigDecimal amount,
                       @NotBlank Status status,
                       @Positive Long customerId,
                       @Positive Long workerId) {
}
