package com.example.project.service;

import com.example.project.dto.user.CustomerRegistrationDTO;
import com.example.project.model.Customer;
import com.example.project.model.User;
import com.example.project.model.Role;
import com.example.project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void registerCustomer(CustomerRegistrationDTO dto) {
        Customer shopper = Customer.builder()
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .email(dto.email())
                .build();

        User user = new User();
        user.setUsername(dto.username());

        user.setPassword(passwordEncoder.encode(dto.password()));

        user.setRole(Role.USER);

        user.setCustomer(shopper);
        user.setEmployee(null);

        userRepository.save(user);
    }
}