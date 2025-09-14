package com.frevi.order.service;

import com.frevi.order.dto.OrderDto;
import com.frevi.order.entity.Order;
import com.frevi.order.mapper.OrderMapper;
import com.frevi.order.repository.OrderJpaRepository;
import com.frevi.order.request.OrderCreateRequest;
import com.frevi.order.request.OrderUpdateRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderJpaRepository orderJpaRepository;
    private final OrderMapper orderMapper;

    public Page<OrderDto> findOrders(Pageable pageable) {
        return orderJpaRepository
                .findAll(pageable)
                .map(orderMapper::toDto);
    }

    @Cacheable(value="orders", key = "#id")
    public OrderDto findOrderById(long id) throws EntityNotFoundException {
        Order order = orderJpaRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        return orderMapper.toDto(order);
    }

    public OrderDto createOrder(OrderCreateRequest orderRequest) {
        Order order = orderJpaRepository.save(orderMapper.fromCreateRequest(orderRequest));

        return orderMapper.toDto(order);
    }

    @CacheEvict(value = "orders", key = "#orderRequest.id()")
    public OrderDto updateOrder(OrderUpdateRequest orderRequest) {
        Order order = orderJpaRepository.save(orderMapper.fromUpdateRequest(orderRequest));

        return orderMapper.toDto(order);
    }

    @CacheEvict(value = "orders", key = "#id")
    public void deleteById(long id) throws EntityNotFoundException {
        if (!orderJpaRepository.existsById(id))
            throw new EntityNotFoundException();

        orderJpaRepository.deleteById(id);
    }
}
