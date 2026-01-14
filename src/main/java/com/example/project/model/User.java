package com.example.project.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "users")
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @PrePersist
    @PreUpdate
    private void validateExclusiveProfile() {
        if (employee != null && customer != null) {
            throw new IllegalStateException("Security Violation: A User cannot be both an Employee and a Customer.");
        }
        if (employee == null && customer == null) {
            throw new IllegalStateException("Data Error: A User must be linked to either an Employee or a Customer profile.");
        }
    }
}

