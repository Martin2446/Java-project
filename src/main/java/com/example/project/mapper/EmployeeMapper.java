package com.example.project.mapper;

import com.example.project.dto.employee.*;
import com.example.project.model.Employee;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EmployeeMapper {

    EmployeeResponseDTO toEmployeeResponseDTO(Employee employee);

    List<EmployeeResponseDTO> toEmployeeResponseDTOList(List<Employee> employees);

    @Mapping(target = "id", ignore = true)
    Employee toEmployee(EmployeeRequestDTO employeeRequestDTO);
}
