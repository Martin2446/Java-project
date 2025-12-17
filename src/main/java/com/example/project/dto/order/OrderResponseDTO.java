package com.example.project.dto.order;

import com.example.project.dto.product.ProductResponseDTO;
import com.example.project.dto.product.ProductSummaryResponseDTO;

import java.util.List;

public record OrderResponseDTO(
        Long orderId,
        String address,
        List<ProductSummaryResponseDTO> products
) {}
