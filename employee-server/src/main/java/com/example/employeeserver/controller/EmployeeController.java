package com.example.employeeserver.controller;

import com.example.employeeserver.dto.EmployeeRequestDto;
import com.example.employeeserver.dto.EmployeeResponseDto;
import com.example.employeeserver.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;
    @PostMapping("/add")
    public ResponseEntity<EmployeeResponseDto> createEmployee(@RequestBody EmployeeRequestDto requestDto){
       return ResponseEntity.status(201).body(employeeService.saveEmployee(requestDto));
    }
    @GetMapping("/get-all")
    public List<EmployeeResponseDto> getAllEmployee(){
        return employeeService.getAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDto> findById(@PathVariable Integer id){
        return new ResponseEntity<>(employeeService.findById(id), HttpStatus.OK);
    }
    @GetMapping("/department-employees/{departmentId}")
    public ResponseEntity<List<EmployeeResponseDto>> getEmployeesByDepartmentId(@PathVariable("departmentId") Integer id){
        return ResponseEntity.ok(employeeService.getDepartmentEmployees(id));
    }
}
