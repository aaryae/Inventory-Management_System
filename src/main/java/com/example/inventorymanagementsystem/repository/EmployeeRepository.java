package com.example.inventorymanagementsystem.repository;

import com.example.inventorymanagementsystem.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    //Find employee by email
    Optional<Employee>findByEmailIgnoreCase(String email);

    //Checks if email exists
    boolean existsByEmailIgnoreCase(String email);

    //Find active employees
    List<Employee> findByIsActiveTrue();

    //Find employees by department
    List<Employee> findByDepartmentIgnoreCase(String department);

    //Find employees by department and active status
    List<Employee> findByDepartmentIgnoreCaseAndIsActiveTrue(String department);

    //Get all departments
    @Query("SELECT DISTINCT e.department FROM Employee e WHERE e.department IS NOT NULL ORDER BY e.department ")
    List<String> findAllDepartments();

    //Find employees with assignments
    @Query("SELECT DISTINCT e FROM Employee e JOIN e.assignments a WHERE a.isActive = true")
    List<Employee> findEmployeesWithActiveAssignments();

    //Find employees without any active assignments
    @Query("SELECT e FROM Employee e WHERE e.isActive = true AND e.employeeId NOT IN" +
            "(SELECT DISTINCT a.employee.employeeId FROM Assignment a WHERE a.isActive = true)")
    List<Employee> findEmployeesWithoutActiveAssignments();

    // Search employees by multiple criteria
    @Query("SELECT e FROM Employee e WHERE e.isActive = true AND " +
            "(LOWER(e.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(e.email) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(e.department) LIKE LOWER(CONCAT('%', :searchTerm, '%')))")
    List<Employee> searchEmployees(@Param("searchTerm") String searchTerm);
}
