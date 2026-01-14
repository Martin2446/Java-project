package com.example.project.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        final String securitySchemeName = "basicAuth";

        return new OpenAPI()
                // 1. Define WHAT kind of security we use (Basic Auth)
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName,
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("basic")))
                // 2. Apply this security to EVERY endpoint in the Swagger UI
                .addSecurityItem(new SecurityRequirement()
                        .addList(securitySchemeName));
    }
}