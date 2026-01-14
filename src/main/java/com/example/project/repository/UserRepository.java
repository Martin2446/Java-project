package com.example.project.repository;

import com.example.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);
    Optional<User> findByEmployeeId(Long employeeId);

    Optional<User> findByUsername(String username);
}