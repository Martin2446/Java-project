package com.example.project.config;

import com.example.project.service.CustomUserDetailsService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .requestMatchers("/admin/**").hasAnyRole("GLOBAL_ADMIN", "ADMIN_WAREHOUSE")

                        .requestMatchers(HttpMethod.POST, "/products/**").hasAnyRole("ADMIN_WAREHOUSE", "GLOBAL_ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/products/**").hasAnyRole("ADMIN_WAREHOUSE", "GLOBAL_ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/products/**").hasAnyRole("ADMIN_WAREHOUSE", "GLOBAL_ADMIN")

                        .requestMatchers(HttpMethod.GET, "/products/**").authenticated()
                        .requestMatchers("/orders/**").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/products/*/order/*").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/products/process-stock/*").authenticated()

                        .requestMatchers(HttpMethod.GET, "/orders/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/orders/**").authenticated()

                        .anyRequest().authenticated()
                )
                .userDetailsService(userDetailsService)
                .httpBasic(basic -> basic.authenticationEntryPoint((request, response, authException) -> {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
                }));

        return http.build();
    }
}