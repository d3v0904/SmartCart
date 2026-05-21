package com.smartcart.userservice.controller;

import com.smartcart.userservice.dto.RegisterRequestDTO;
import com.smartcart.userservice.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.smartcart.userservice.dto.LoginRequestDTO;

@RestController
@RequestMapping("/api/users")

public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String registerUser(@Valid @RequestBody RegisterRequestDTO requestDTO) {

        return userService.registerUser(requestDTO);
    }

    @PostMapping("/login")
    public String loginUser(@Valid @RequestBody LoginRequestDTO requestDTO) {

        return userService.loginUser(requestDTO);
    }
}