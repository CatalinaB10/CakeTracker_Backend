package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin("*")
public class BirthdayController {

    private final BirthdayService birthdayService;

    public BirthdayController(BirthdayService birthdayService){
        this.birthdayService = birthdayService;
    }

    // get all employees
    @GetMapping("/employees/all")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(){
        return ResponseEntity.ok(birthdayService.getByClosestBirthday());
    }

    // get all employees with a birthday today
    @GetMapping("/employees/birthday_today")
    public ResponseEntity<List<EmployeeDTO>> getTodaysBirthdays(){
         return ResponseEntity.ok(birthdayService.getTodaysBirthdays());
    }

    // get employee by id -> to see his/her birthdate
    @GetMapping("/employees/{employeeId}")
    public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable("employeeId") UUID employeeId){
        return ResponseEntity.ok(birthdayService.getEmployee(employeeId));
    }

    // create employee
    @PostMapping("/employees/create")
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO){
        return ResponseEntity.ok(birthdayService.createEmployee(employeeDTO));
    }

    // update employee
    @PutMapping("/employees/update/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable("employeeId") UUID employeeId, @RequestBody EmployeeDTO employeeDTO){
        return ResponseEntity.ok(birthdayService.updateEmployee(employeeId, employeeDTO));
    }

    // delete employee
    @DeleteMapping("/employees/delete/{employeeId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("employeeId") UUID employeeId){
        birthdayService.deleteEmployee(employeeId);
        return ResponseEntity.ok("Employee deleted successfully!");
    }

}
