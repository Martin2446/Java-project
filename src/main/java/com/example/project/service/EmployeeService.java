package com.example.project.service;

import com.example.project.controller.EmployeeController;
import com.example.project.dto.employee.EmployeeRequestDTO;
import com.example.project.mapper.EmployeeMapper;
import com.example.project.model.Employee;
import com.example.project.repository.EmployeeRepository;
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

    @Transactional
    public Employee saveEmployee(EmployeeRequestDTO employeeRequestDTO)
    {
        Employee employee = employeeMapper.toEmployee(employeeRequestDTO);
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees()
    {
        return employeeRepository.findAll();
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
