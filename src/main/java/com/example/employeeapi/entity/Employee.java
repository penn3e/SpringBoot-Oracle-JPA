package com.example.employeeapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "EMPLOYEES")
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    @Column(name = "NAME", nullable = false, length = 100)
    private String name;

    @Size(max = 100, message = "Job title must be less than 100 characters")
    @Column(name = "JOB_TITLE", length = 100)
    private String jobTitle;

    @NotNull(message = "Salary is required")
    @DecimalMin(value = "0.0", message = "Salary must be a positive number")
    @Column(name = "SALARY", precision = 10, scale = 2)
    private BigDecimal salary;
}
