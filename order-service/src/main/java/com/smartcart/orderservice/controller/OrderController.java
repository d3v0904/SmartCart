package com.smartcart.orderservice.controller;

import com.smartcart.orderservice.dto.OrderRequestDTO;
import com.smartcart.orderservice.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")

public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public String placeOrder(
            @Valid @RequestBody OrderRequestDTO requestDTO) {

        return orderService.placeOrder(requestDTO);
    }
}
