package com.smartcart.userservice.service;

import com.smartcart.userservice.dto.RegisterRequestDTO;
import com.smartcart.userservice.entity.UserEntity;
import com.smartcart.userservice.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.smartcart.userservice.dto.LoginRequestDTO;
import com.smartcart.userservice.config.JwtUtil;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public String registerUser(RegisterRequestDTO requestDTO) {

        if (userRepository.findByEmail(requestDTO.getEmail()).isPresent()) {
            return "Email already exists";
        }

        UserEntity user = UserEntity.builder()
                .name(requestDTO.getName())
                .email(requestDTO.getEmail())
                .password(passwordEncoder.encode(requestDTO.getPassword()))
                .build();

        userRepository.save(user);

        return "User registered successfully";
    }

    public String loginUser(LoginRequestDTO requestDTO) {

        UserEntity user = userRepository.findByEmail(requestDTO.getEmail())
                .orElse(null);

        if (user == null) {
            return "User not found";
        }

        boolean passwordMatches = passwordEncoder.matches(
                requestDTO.getPassword(),
                user.getPassword()
        );

        if (!passwordMatches) {
            return "Invalid password";
        }

        return jwtUtil.generateToken(user.getEmail());
    }


}
