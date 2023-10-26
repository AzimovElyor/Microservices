package com.example.employeeserver.service;

import com.example.employeeserver.dto.EmployeeRequestDto;
import com.example.employeeserver.dto.EmployeeResponseDto;
import com.example.employeeserver.module.Employee;
import com.example.employeeserver.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    public EmployeeResponseDto saveEmployee(EmployeeRequestDto requestDto){
        if(!requestDto.getPassword().equals(requestDto.getConfirmPassword())){
            throw new RuntimeException("Incorrect confirm password");
        }
        if (employeeRepository.existsByEmail(requestDto.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        Employee employee = Employee.builder()
                .firstName(requestDto.getFirstName())
                .lastName(requestDto.getLastName())
                .email(requestDto.getEmail())
                .password(requestDto.getPassword())
                .departmentId(requestDto.getDepartmentId())
                .build();
        employeeRepository.save(employee);
        return mapEmployeeToResponse(employee);

    }

    private EmployeeResponseDto mapEmployeeToResponse( Employee employee) {
        return EmployeeResponseDto.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
              /*  .age(Period.between(employee.getDateOfBirth(), LocalDate.now()).getYears())*/
                .createdDate(employee.getCreatedDate())
                .departmentId(employee.getDepartmentId())
                .build();
    }

    public List<EmployeeResponseDto> getAll() {
        return employeeRepository.findAll().stream()
                .map(this::mapEmployeeToResponse)
                .collect(Collectors.toList());
    }
    public EmployeeResponseDto findById(Integer id){
        return mapEmployeeToResponse(employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found")));
    }
    public List<EmployeeResponseDto> getDepartmentEmployees(Integer departmentId){
        return employeeRepository.findByDepartmentId(departmentId)
                .stream()
                .map(this::mapEmployeeToResponse)
                .collect(Collectors.toList());
    }
}
