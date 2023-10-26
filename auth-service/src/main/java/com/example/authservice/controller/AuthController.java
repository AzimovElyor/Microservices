package com.example.authservice.controller;

import com.example.authservice.dto.JwtResponse;
import com.example.authservice.dto.UserRequestDto;
import com.example.authservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService useService;
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public JwtResponse register(@RequestBody UserRequestDto requestDto){
         return useService.register(requestDto);
    }
}
