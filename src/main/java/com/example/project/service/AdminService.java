package com.example.project.service;

import com.example.project.dto.user.AdminRegistrationDTO;
import com.example.project.model.*;
import com.example.project.repository.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    @Transactional
    public void registerStaff(AdminRegistrationDTO dto) {

        Department dept = departmentRepository.findByName(dto.departmentName())
                .orElseThrow(() -> new EntityNotFoundException("Department not found"));

        Employee newEmployee = Employee.builder()
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .department(dept)
                .build();

        User newUser = new User();
        newUser.setUsername(dto.username());
        newUser.setPassword(dto.password()); // Later we will learn to "encrypt" this
        newUser.setEmployee(newEmployee);

        if (dept.getName().equalsIgnoreCase("WAREHOUSE")) {
            newUser.setRole(Role.ADMIN_WAREHOUSE);
        } else if (dept.getName().equalsIgnoreCase("PRODUCTS")) {
            newUser.setRole(Role.ADMIN_PRODUCTS);
        }

        userRepository.save(newUser);
    }
}