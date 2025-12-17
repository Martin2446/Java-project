package com.example.project.dto.product;

import com.example.project.model.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.List;

public record ProductRequestDTO (

    @NotBlank(message = "Product name cannot be null or empty")
    @Size(min = 2, max = 40, message = "Product name must be between 2 and 40 characters")
    String name,

    //@NotBlank(message = "Product price cannot be null or empty")
    @Positive(message = "product price must be a positive number")
    double price,

    @Size(min = 2, max = 30, message = "Product name must be between 2 and 40 characters")
    String deliveryAddress
) {
    public ProductRequestDTO
    {
        name = name.trim();
        deliveryAddress = deliveryAddress.trim();
    }
}
