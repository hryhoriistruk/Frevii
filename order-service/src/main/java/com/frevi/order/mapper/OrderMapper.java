package com.frevi.order.mapper;

import com.frevi.order.dto.OrderDto;
import com.frevi.order.entity.Order;
import com.frevi.order.request.OrderCreateRequest;
import com.frevi.order.request.OrderUpdateRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order fromCreateRequest(OrderCreateRequest orderRequest);

    OrderDto toDto(Order order);

    Order fromUpdateRequest(OrderUpdateRequest orderUpdateRequest);
}
