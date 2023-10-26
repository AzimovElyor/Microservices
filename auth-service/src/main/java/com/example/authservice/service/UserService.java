package com.example.authservice.service;

import com.example.authservice.dto.JwtResponse;
import com.example.authservice.dto.UserRequestDto;
import com.example.authservice.jwt.JwtService;
import com.example.authservice.module.UserEntity;
import com.example.authservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    public JwtResponse register(UserRequestDto userRequestDto) {
        if(!userRequestDto.getPassword().equals(userRequestDto.getConfirmPassword())){
            throw new RuntimeException("Incorrect confirm password");
        }
        if (userRepository.existsByEmail(userRequestDto.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        UserEntity user = UserEntity.builder()
                .firstName(userRequestDto.getFirstName())
                .lastname(userRequestDto.getLastName())
                .email(userRequestDto.getEmail())
                .dateOfBirth(userRequestDto.getDateOfBirth())
                .role(userRequestDto.getRole())
                .password(encoder.encode(userRequestDto.getPassword()))
                .build();
        userRepository.save(user);
        return JwtResponse.builder().token(jwtService.generateToken(user)).build();
    }
}
