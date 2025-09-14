package com.frevi.order.listener;

import com.frevi.order.request.OrderCreateRequest;
import com.frevi.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderKafkaListener {
    private final OrderService orderService;

    @KafkaListener(topics = "order-creation",
            groupId = "order-creation",
            containerFactory = "kafkaListenerContainerFactoryHabitSentEvent")
    public void listenerHabitSentEvent(@Valid OrderCreateRequest event) {
        orderService.createOrder(event);
    }
}
