package com.smartcart.orderservice.controller;

import com.smartcart.orderservice.dto.OrderRequestDTO;
import com.smartcart.orderservice.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController //For REST APIs
@RequestMapping("/api/orders") //THis is defines the base URL for this service to work

public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    } //This is Dependency Injection

    @PostMapping
    public String placeOrder(
            @Valid @RequestBody OrderRequestDTO requestDTO) { //@requestbody converts JSON into OrderRequestDTO

        return orderService.placeOrder(requestDTO); //Basically it forwards request from client to order service
    }
}
