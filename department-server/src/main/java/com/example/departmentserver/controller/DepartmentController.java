package com.example.departmentserver.controller;

import com.example.departmentserver.dto.DepartmentRequestDto;
import com.example.departmentserver.dto.DepartmentResponseDto;
import com.example.departmentserver.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/department")
public class DepartmentController {
    private final DepartmentService departmentService;
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public DepartmentResponseDto create(@RequestBody DepartmentRequestDto requestDto){
        return departmentService.createDepartment(requestDto);
    }
    @GetMapping("/get-all")
    public ResponseEntity<List<DepartmentResponseDto>> getAll(){
        return ResponseEntity.ok(departmentService.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponseDto> findById(@PathVariable Integer id){
        return new ResponseEntity<>(departmentService.findById(id),HttpStatus.OK);
    }
}
