package com.example.employeeserver.repository;

import com.example.employeeserver.module.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
   Optional<Employee> findByEmail(String email);
   Boolean existsByEmail(String email);
   List<Employee> findByDepartmentId(Integer departmentId);
}
