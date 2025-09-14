package com.frevi.order.controller;

import com.frevi.order.dto.OrderDto;
import com.frevi.order.request.OrderUpdateRequest;
import com.frevi.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;

    @PutMapping
    public ResponseEntity<OrderDto> updateOrder(@Valid @RequestBody OrderUpdateRequest orderUpdateRequest) {
        return ResponseEntity.ok(orderService.updateOrder(orderUpdateRequest));
    }

    @GetMapping("{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable long id) {
        return ResponseEntity.ok(orderService.findOrderById(id));
    }

    public ResponseEntity<PagedModel<EntityModel<OrderDto>>> findAllOrders(Pageable pageable, PagedResourcesAssembler<OrderDto> assembler) {
        Page<OrderDto> orders = orderService.findOrders(pageable);

        return ResponseEntity.ok(assembler.toModel(orders));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        orderService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
