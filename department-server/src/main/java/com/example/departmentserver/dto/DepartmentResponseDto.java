package com.example.departmentserver.dto;

import com.example.departmentserver.model.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepartmentResponseDto {
    private Integer id;
    private String name;
    private List<Employee>  employees = new ArrayList<>();
    private LocalDateTime createdDate;
}
