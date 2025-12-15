package com.example.project.startup;

import com.example.project.model.Employee;
import com.example.project.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Profile("dev")
@Order(0)
@Component
@RequiredArgsConstructor
public class EmployeeSeeder implements CommandLineRunner {

    private final EmployeeRepository employeeRepository;

    @Override
    public void run(String... args) throws Exception
    {
        Employee employee1 = Employee.builder()
                .firstName("Martin")
                .lastName("Grozev")
                .phoneNumber("0882717375")
                .build();

        Employee employee2 = Employee.builder()
                .firstName("Ivan")
                .lastName("Ivanov")
                .phoneNumber("0815530852")
                .build();

        employeeRepository.save(employee1);
        employeeRepository.save(employee2);

        System.out.println(" 2 Employees were seeded!");
    }
}
