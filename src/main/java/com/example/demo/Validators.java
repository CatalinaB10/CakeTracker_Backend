package com.example.demo;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Validators {
    public static void validateEmployee(EmployeeDTO employeeDTO) {
        if (employeeDTO.getFirstName() == null || employeeDTO.getFirstName().isEmpty()) {
            throw new IllegalArgumentException("First name is required");
        }
        if (employeeDTO.getLastName() == null || employeeDTO.getLastName().isEmpty()) {
            throw new IllegalArgumentException("Last name is required");
        }
        if (employeeDTO.getBirthDate() == null) {
            throw new IllegalArgumentException("Birth date is required");
        }
        if (employeeDTO.getCity() == null || employeeDTO.getCity().isEmpty()) {
            throw new IllegalArgumentException("City is required");
        }
        if (employeeDTO.getCountry() == null || employeeDTO.getCountry().isEmpty()) {
            throw new IllegalArgumentException("Country is required");
        }
        if(employeeDTO.getBirthDate().isAfter(java.time.LocalDate.now())){
            throw new IllegalArgumentException("Birth date cannot be in the future");
        }
        if(employeeDTO.getBirthDate().isBefore(java.time.LocalDate.of(1900, 1, 1))){
            throw new IllegalArgumentException("Birth date cannot be before 1900-01-01");
        }
        if(!isOlderThan18(employeeDTO.getBirthDate().toString())){
            throw new IllegalArgumentException("Employee must be older than 18 years");
        }
    }
    public static boolean isOlderThan18(String birthdateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate birthdate = LocalDate.parse(birthdateString, formatter);
            LocalDate currentDate = LocalDate.now();
            Period age = Period.between(birthdate, currentDate);

            return age.getYears() >= 18;
        } catch (DateTimeParseException e) {
            System.err.println("Invalid date format. Please use 'yyyy-MM-dd'.");
            return false;
        }
    }
}
