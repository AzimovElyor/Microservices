package com.example.authservice.dto;

import com.example.authservice.enums.UserRole;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter    ;

import java.time.LocalDate;

@Data
@Getter
@Setter
@Builder
public class UserRequestDto {
    private String firstName;
    private String lastName;
    private String email;
    private UserRole role;
    private String password;
    private String confirmPassword;
    private LocalDate dateOfBirth;
}
