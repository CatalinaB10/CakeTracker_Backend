package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

public interface BirthdayService {
    List<EmployeeDTO> getAllEmployees();
    EmployeeDTO getEmployee(UUID employeeId);
    List<EmployeeDTO> getTodaysBirthdays();
    EmployeeDTO createEmployee(EmployeeDTO employeeDTO);
    EmployeeDTO updateEmployee(UUID employeeId, EmployeeDTO employeeDTO);
    void deleteEmployee(UUID employeeId);
}
