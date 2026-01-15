package com.example.project.dto.order;

import com.example.project.dto.product.ProductSummaryResponseDTO;

import java.util.List;

public record OrderResponseDTO(
        Long id,
        String address,
        String status,
        List<ProductSummaryResponseDTO> products
) {}
