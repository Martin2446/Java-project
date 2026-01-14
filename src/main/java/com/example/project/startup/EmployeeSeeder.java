package com.example.project.startup;

import com.example.project.model.Department;
import com.example.project.model.Employee;
import com.example.project.model.User;
import com.example.project.model.Role;
import com.example.project.repository.EmployeeRepository;
import com.example.project.repository.DepartmentRepository;
import com.example.project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Profile("dev")
@Order(0)
@Component
@RequiredArgsConstructor
public class EmployeeSeeder implements CommandLineRunner {

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        Department department1 = new Department("IT");
        Department department2 = new Department("WAREHOUSE");

        departmentRepository.save(department1);
        departmentRepository.save(department2);


        Employee employee1 = Employee.builder()
                .firstName("Martin")
                .lastName("Grozev")
                .phoneNumber("0882717375")
                .department(department1)
                .build();

        Employee employee2 = Employee.builder()
                .firstName("Ivan")
                .lastName("Ivanov")
                .phoneNumber("0815530852")
                .department(department2)
                .build();

        employeeRepository.save(employee1);
        employeeRepository.save(employee2);

        if (userRepository.findByUsername("admin").isEmpty()) {
            User adminUser = new User();
            adminUser.setUsername("admin");
            adminUser.setPassword(passwordEncoder.encode("1234"));
            adminUser.setRole(Role.GLOBAL_ADMIN);

            adminUser.setEmployee(employee1);
            adminUser.setCustomer(null);

            userRepository.save(adminUser);

            System.out.println("2 Employees were seeded!");
        }
    }
}
