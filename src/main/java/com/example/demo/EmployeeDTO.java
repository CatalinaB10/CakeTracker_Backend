package com.example.demo;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EmployeeDTO {
    private UUID employeeId;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String city;
    private String country;
}
