package com.smartcart.orderservice.service;

import com.smartcart.orderservice.client.ProductClient;
import com.smartcart.orderservice.dto.OrderRequestDTO;
import com.smartcart.orderservice.entity.OrderEntity;
import com.smartcart.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductClient productClient;

    public OrderService(OrderRepository orderRepository,
                        ProductClient productClient) {

        this.orderRepository = orderRepository;
        this.productClient = productClient;
    }
    @CircuitBreaker(name = "productService",
            fallbackMethod = "fallbackOrder")

    public String placeOrder(OrderRequestDTO requestDTO) {

        Object product = productClient
                .getProductById(requestDTO.getProductId());

        if (product == null) {
            return "Product not found";
        }

        OrderEntity order = OrderEntity.builder()
                .productId(requestDTO.getProductId())
                .quantity(requestDTO.getQuantity())
                .customerName(requestDTO.getCustomerName())
                .totalPrice(1000.0)
                .build();

        orderRepository.save(order);

        return "Order placed successfully";
    }
    public String fallbackOrder(
            OrderRequestDTO requestDTO,
            Exception ex) {

        return "Product Service is currently unavailable. Please try again later.";
    }
}
