package com.example.employeeapi.controller;

import com.example.employeeapi.entity.Employee;
import com.example.employeeapi.repository.EmployeeRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeRepository repository;

    // Get all employees with pagination
    @GetMapping
    public ResponseEntity<Page<Employee>> getEmployees(Pageable pageable) {
        Page<Employee> employees = repository.findAll(pageable);
        return ResponseEntity.ok(employees);
    }

    // Get employee by ID
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create a new employee
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee) {
        Employee savedEmployee = repository.save(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
    }

    // Update an existing employee
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable Long id, 
            @Valid @RequestBody Employee updatedEmployee) {
        return repository.findById(id)
                .map(employee -> {
                    employee.setName(updatedEmployee.getName());
                    employee.setJobTitle(updatedEmployee.getJobTitle());
                    employee.setSalary(updatedEmployee.getSalary());
                    return ResponseEntity.ok(repository.save(employee));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete an employee
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Additional query methods
    @GetMapping("/by-name")
    public ResponseEntity<List<Employee>> getEmployeesByName(@RequestParam String name) {
        List<Employee> employees = repository.findByName(name);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/by-job-title")
    public ResponseEntity<List<Employee>> getEmployeesByJobTitle(@RequestParam String jobTitle) {
        List<Employee> employees = repository.findByJobTitle(jobTitle);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/high-salary")
    public ResponseEntity<List<Employee>> getHighSalaryEmployees(@RequestParam Double threshold) {
        List<Employee> employees = repository.findBySalaryGreaterThan(threshold);
        return ResponseEntity.ok(employees);
    }
}
