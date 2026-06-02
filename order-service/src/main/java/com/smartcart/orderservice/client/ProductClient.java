package com.smartcart.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient; //For Feign Communication
import org.springframework.web.bind.annotation.GetMapping;   //Line 4-5 are for which API endpoint to call and whatparameters to send
import org.springframework.web.bind.annotation.PathVariable;

// It tells spring where to communicate to
@FeignClient(name = "product-service",
        url = "http://localhost:8080")

public interface ProductClient {

    @GetMapping("/api/products/{id}") //Fetches GET Request based on Id

    Object getProductById(@PathVariable Long id); // this is a method which send GET Request when called
}