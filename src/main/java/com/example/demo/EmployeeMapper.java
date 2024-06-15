package com.example.demo;

public class EmployeeMapper{
    public static EmployeeDTO mapToDto(Employee employee){
        return EmployeeDTO.builder()
                .employeeId(employee.getEmployeeId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .birthDate(employee.getBirthDate())
                .city(employee.getCity())
                .country(employee.getCountry())
                .build();
    }

    public static Employee mapToEntity(EmployeeDTO employeeDTO){
        return Employee.builder()
                .employeeId(employeeDTO.getEmployeeId())
                .firstName(employeeDTO.getFirstName())
                .lastName(employeeDTO.getLastName())
                .birthDate(employeeDTO.getBirthDate())
                .city(employeeDTO.getCity())
                .country(employeeDTO.getCountry())
                .build();
    }

}
