package com.example.employeeserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class EmployeeResponseDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private Integer age;
    private Integer departmentId;
    private LocalDateTime createdDate;
}
