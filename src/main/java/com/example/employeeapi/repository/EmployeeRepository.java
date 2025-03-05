package com.example.employeeapi.repository;

import com.example.employeeapi.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // Find employees by name
    List<Employee> findByName(String name);
    
    // Find employees by job title
    List<Employee> findByJobTitle(String jobTitle);
    
    // Find employees with salary greater than a given amount
    List<Employee> findBySalaryGreaterThan(Double salary);
    
    // Custom query to find employees by name containing a keyword
    @Query("SELECT e FROM Employee e WHERE e.name LIKE %:keyword%")
    List<Employee> findByNameContaining(@Param("keyword") String keyword);
}
