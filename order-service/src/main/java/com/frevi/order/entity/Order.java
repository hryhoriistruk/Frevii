package com.frevi.order.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class Order {
    @Id
    private Long id;

    private BigDecimal amount;

    private Status status;

    private long customerId;

    private long workerId;
}
