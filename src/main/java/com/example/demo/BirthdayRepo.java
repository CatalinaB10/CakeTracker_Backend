package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface BirthdayRepo extends JpaRepository<Employee, UUID>{
    List<Employee> findByBirthDate(LocalDate birthDate);
}
