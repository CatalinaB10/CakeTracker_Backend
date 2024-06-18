package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class BirdthdayServImpl implements BirthdayService{
    private final BirthdayRepo birthdayRepo;

    public BirdthdayServImpl(BirthdayRepo birthdayRepo){
        this.birthdayRepo = birthdayRepo;
    }

    @Override
    public List<EmployeeDTO> getAllEmployees(){
        List<Employee> employees = birthdayRepo.findAll();
        List<EmployeeDTO> employeeDTOs = new ArrayList<>();
        for(Employee employee : employees){
            employeeDTOs.add(new EmployeeDTO(employee.getEmployeeId(), employee.getFirstName(), employee.getLastName(), employee.getBirthDate(), employee.getCity(), employee.getCountry()));
        }
        return employeeDTOs;
    }
    @Override

    public EmployeeDTO getEmployee(UUID employeeId) {
        Employee employee = birthdayRepo.findById(employeeId).orElseThrow(() -> new RuntimeException("Employee not found"));
        return EmployeeMapper.mapToDto(employee);
    }
    @Override

    public List<EmployeeDTO> getTodaysBirthdays(){
        List<Employee> employees = birthdayRepo.findAll();
        //filter employees by today's date
        LocalDate today = LocalDate.now();
        int day = today.getDayOfMonth();
        int month = today.getMonthValue();

        employees.removeIf(employee -> (!(employee.getBirthDate().getMonthValue() == month) || !(employee.getBirthDate().getDayOfMonth() == day)));
        log.info("Employee with birthday today: " + employees.size());
        return employees.stream().map(EmployeeMapper::mapToDto).toList();
    }
    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO){
        try {
            Validators.validateEmployee(employeeDTO);
        }catch (IllegalArgumentException e){
            throw new RuntimeException(e.getMessage());
        }
        Employee employee = Employee.builder()
                .firstName(employeeDTO.getFirstName())
                .lastName(employeeDTO.getLastName())
                .birthDate(employeeDTO.getBirthDate())
                .city(employeeDTO.getCity())
                .country(employeeDTO.getCountry())
                .build();
        Employee savedEmployee = birthdayRepo.save(employee);
        log.info("Employee created with id: " + savedEmployee.getEmployeeId());
        return EmployeeMapper.mapToDto(savedEmployee);
    }
    @Override
    public EmployeeDTO updateEmployee(UUID employeeId, EmployeeDTO employeeDTO) {
        Employee employee = birthdayRepo.findById(employeeId).orElseThrow(() -> new RuntimeException("Employee not found"));
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setBirthDate(employeeDTO.getBirthDate());
        employee.setCity(employeeDTO.getCity());
        employee.setCountry(employeeDTO.getCountry());
        Employee savedEmployee = birthdayRepo.save(employee);
        log.info("Employee updated with id: " + savedEmployee.getEmployeeId());
        return EmployeeMapper.mapToDto(savedEmployee);
    }
    @Override
    public void deleteEmployee(UUID employeeId){
        birthdayRepo.deleteById(employeeId);
        log.info("Employee deleted with id: " + employeeId);
    }

    @Override
    public List<EmployeeDTO> getByClosestBirthday() {
        List<Employee> birthdays = birthdayRepo.findAll();
        LocalDate currentDate = LocalDate.now();

        // Sort list by closest birthdate to current date
        birthdays.sort(Comparator.comparingLong(employee -> {
            LocalDate nextBirthday = employee.getBirthDate().withYear(currentDate.getYear());
            if (nextBirthday.isBefore(currentDate) || nextBirthday.isEqual(currentDate)) {
                nextBirthday = nextBirthday.plusYears(1);
            }
            return ChronoUnit.DAYS.between(currentDate, nextBirthday);
        }));

        return birthdays.stream().map(EmployeeMapper::mapToDto).toList();
    }

}
