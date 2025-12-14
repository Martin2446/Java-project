package com.example.project.mapper;

import com.example.project.dto.employee.*;
import com.example.project.model.Employee;
import com.example.project.repository.projection.EmployeeNameProjection;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EmployeeMapper {

    @Mapping(target = "departmentId", source = "department.id")
    @Mapping(target = "departmentName", source = "department.name")
    EmployeeResponseDTO toEmployeeResponseDTO(Employee employee);

    List<EmployeeResponseDTO> toEmployeeResponseDTOList(List<Employee> employees);

    EmployeeSummaryResponseDTO toEmployeeSummaryResponseDTO(Employee employee);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "department", ignore = true)
    Employee toEmployee(EmployeeRequestDTO employeeRequestDTO);

    List<EmployeeNameResponseDTO> toEmployeeNameResponseDTOList(List<EmployeeNameProjection> employeeNameProjections);

    List<EmployeeNameResponseDTO> toEmployeeNameResponseDTOListFromProjectionDTOList(List<EmployeeNameProjectionDTO> employeeNameProjectionDTOs);

    @Mapping(target = "department", ignore = true)
    void updateEmployeeFromRequestDTO(@MappingTarget Employee entity, EmployeeRequestDTO dto);

    @AfterMapping
    default void trimEmployeeFields(@MappingTarget Employee entity, EmployeeRequestDTO dto) {
        entity.setFirstName(dto.firstName().trim());
        entity.setLastName(dto.lastName().trim());
        if (entity.getPhoneNumber() != null) entity.setPhoneNumber(dto.phoneNumber().trim());
    }
}
