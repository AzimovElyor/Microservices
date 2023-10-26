package com.example.departmentserver.service;

import com.example.departmentserver.client.EmployeeClient;
import com.example.departmentserver.dto.DepartmentRequestDto;
import com.example.departmentserver.dto.DepartmentResponseDto;
import com.example.departmentserver.model.Department;
import com.example.departmentserver.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private EmployeeClient employeeClient;
    public DepartmentResponseDto createDepartment(DepartmentRequestDto requestDto){
        if (departmentRepository.existsByName(requestDto.getName())) {
            throw new RuntimeException("Department name already exists");
        }
        Department department = Department.builder()
                .name(requestDto.getName())
                .build();
        departmentRepository.save(department);
        return DepartmentResponseDto.builder().
                id(department.getId())
                .name(department.getName())
                .createdDate(department.getCreatedDate())
                .build();
    }
    public List<DepartmentResponseDto> getAll(){
        return departmentRepository.findAll().stream()
                .map(this::mapDepartmentToResponse
                        )
                .collect(Collectors.toList());
    }



    public DepartmentResponseDto findById(Integer id){
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        return mapDepartmentToResponse(department);
    }
    private  DepartmentResponseDto mapDepartmentToResponse(Department department) {
        return DepartmentResponseDto.builder().
                id(department.getId())
                .name(department.getName())
                .createdDate(department.getCreatedDate())
                .employees(employeeClient.getEmployeesByDepartmentId(department.getId()))
                .build();
    }
}
