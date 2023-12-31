package com.example.departmentserver.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private Integer age;
    private Integer departmentId;
    private LocalDateTime createdDate;
}
