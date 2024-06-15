package com.example.demo;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy="uuid2")
    private UUID employeeId;

    @Column(name = "first_name", unique = true)
    private String firstName;

    @Column(name = "last_name", unique = true)
    private String lastName;

    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    @NonNull
    private LocalDate birthDate;

    @Column(name = "country", unique = true)
    private String country;

    @Column(name = "city", unique = true)
    private String city;

}
