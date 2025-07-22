package com.example.inventorymanagementsystem.service.impl;

import com.example.inventorymanagementsystem.dtos.EmployeeUpdateDTO;
import com.example.inventorymanagementsystem.dtos.request.employee.EmployeeRequestDTO;
import com.example.inventorymanagementsystem.dtos.response.employee.EmployeeResponseDTO;
import com.example.inventorymanagementsystem.exception.ConflictException;
import com.example.inventorymanagementsystem.exception.DuplicateEmployeeException;
import com.example.inventorymanagementsystem.exception.EmployeeNotFoundExceptionHandler;
import com.example.inventorymanagementsystem.helper.MessageConstant;
import com.example.inventorymanagementsystem.model.Employee;
import com.example.inventorymanagementsystem.repository.EmployeeRepository;
import com.example.inventorymanagementsystem.service.EmployeeService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO employeeRequestDTO) {
        if (employeeRepository.existsByEmail(employeeRequestDTO.email())){
            throw new DuplicateEmployeeException("Employee already exists with this email"+ employeeRequestDTO.email());
        }

        Employee employee = new Employee();
        employee.setName(employeeRequestDTO.name());
        employee.setEmail(employeeRequestDTO.email());
        employee.setDepartment(employeeRequestDTO.department());

        Employee savedEmployee = employeeRepository.save(employee);
        return convertToDto(savedEmployee);
    }

    @Override
    @Transactional
    public List<EmployeeResponseDTO> createEmployees(List<EmployeeRequestDTO> employeeRequestDTO) {
        return employeeRequestDTO.stream().map(this::createEmployee).toList();
    }

    @Override
    public EmployeeResponseDTO getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundExceptionHandler(
                        MessageConstant.EMPLOYEE,"id", employeeId));
        return convertToDto(employee);
    }

    @Override
    public EmployeeResponseDTO getEmployeeByEmail(String email) {
        Employee employee = employeeRepository.findByEmail(email)
                .orElseThrow(() -> new EmployeeNotFoundExceptionHandler(
                        MessageConstant.EMPLOYEE, "email", email));
        return convertToDto(employee);
    }



    @Override
    public List<EmployeeResponseDTO> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(this::convertToDto)
                .toList();
    }

    @Override
    public EmployeeResponseDTO updateEmployee(Long employeeId, EmployeeUpdateDTO updateDTO) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundExceptionHandler(
                        MessageConstant.EMPLOYEE, "id", employeeId));
        //Check if email is being updated and if it already exists
        if (updateDTO.email() != null && !updateDTO.email().equals(employee.getEmail()) && employeeRepository.existsByEmail(updateDTO.email())) {
            throw new ConflictException("An employee with this email" + updateDTO.email() + "already exists");
        }
        //Update Fields if provided
        if (updateDTO.name() != null) {
            employee.setName(updateDTO.name());
        }

        if (updateDTO.email() != null){
            employee.setEmail(updateDTO.email());
        }

        if (updateDTO.department() != null) {
            employee.setDepartment(updateDTO.department());
        }
        Employee updatedEmployee = employeeRepository.save(employee);
        return convertToDto(updatedEmployee);
    }


    @Override
    public void deleteEmployee(Long employeeId) {
        // Check if the employee exists before deleting
        if (!employeeRepository.existsById(employeeId)) {
            throw new EmployeeNotFoundExceptionHandler(MessageConstant.EMPLOYEE,"id", employeeId);
        }
        employeeRepository.deleteById(employeeId);
    }


    private EmployeeResponseDTO convertToDto(Employee employee) {
        return new EmployeeResponseDTO(
                employee.getEmployeeId(),
                employee.getName(),
                employee.getEmail(),
                employee.getDepartment(),
                employee.getCreatedAt(),
                employee.getUpdatedAt()
        );
    }
}