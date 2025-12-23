package com.example.project.dto.order;

import com.example.project.dto.product.ProductResponseDTO;

import java.util.List;

public record OrderResponseDTO(
        Long id,
        String address,
        List<ProductResponseDTO> products
) {}
