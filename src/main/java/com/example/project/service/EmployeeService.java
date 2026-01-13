package com.example.project.service;

import com.example.project.controller.EmployeeController;
import com.example.project.dto.employee.EmployeeRequestDTO;
import com.example.project.mapper.EmployeeMapper;
import com.example.project.model.Department;
import com.example.project.model.Employee;
import com.example.project.model.Role;
import com.example.project.repository.DepartmentRepository;
import com.example.project.repository.EmployeeRepository;
import com.example.project.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EmployeeService {

    private final EmployeeMapper employeeMapper;
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final UserRepository userRepository;

    @Transactional

    public List<Employee> getAllEmployees()
    {
        return employeeRepository.findAll();
    }

    @Transactional
    public Employee updateEmployee(Long id, EmployeeRequestDTO dto) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));

        existingEmployee.setFirstName(dto.firstName());
        existingEmployee.setLastName(dto.lastName());
        existingEmployee.setPhoneNumber(dto.phoneNumber());

        if (dto.departmentName() != null && !dto.departmentName().isBlank()) {
            Department dept = departmentRepository.findByName(dto.departmentName())
                    .orElseThrow(() -> new EntityNotFoundException("Department not found"));

            existingEmployee.setDepartment(dept);

            userRepository.findByEmployeeId(id).ifPresent(user -> {
                user.setRole(Role.fromDepartmentName(dept.getName()));
                userRepository.save(user);
            });
        }

        return employeeRepository.save(existingEmployee);
    }
    @Transactional
    public void deleteEmployee(Long id)
    {
        if(employeeRepository.existsById(id))
            employeeRepository.deleteById(id);
        else
            throw new EntityNotFoundException("Employee with id " + id + " does not  exist!");
    }
}
