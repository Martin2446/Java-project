package com.example.project.controller;

import com.example.project.dto.employee.EmployeeRequestDTO;
import com.example.project.dto.employee.EmployeeResponseDTO;
import com.example.project.mapper.EmployeeMapper;
import com.example.project.model.Employee;
import com.example.project.service.EmployeeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
@Validated
public class EmployeeController {

    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;

    @PostMapping
    public ResponseEntity<EmployeeResponseDTO> saveEmployee(@Valid @RequestBody EmployeeRequestDTO employeeRequestDTO) {
        Employee savedEmployee = employeeService.saveEmployee(employeeRequestDTO);
        return new ResponseEntity<>(employeeMapper.toEmployeeResponseDTO(savedEmployee), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponseDTO>> getAllEmployees() {
        return new ResponseEntity<>(employeeMapper.toEmployeeResponseDTOList(employeeService.getAllEmployees()), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable @Positive Long id)
    {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}
