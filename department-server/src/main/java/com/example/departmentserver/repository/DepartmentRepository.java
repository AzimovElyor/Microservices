package com.example.departmentserver.repository;

import com.example.departmentserver.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department,Integer> {
    Boolean existsByName(String name);
}
