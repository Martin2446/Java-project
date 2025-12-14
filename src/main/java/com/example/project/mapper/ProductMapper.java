package com.example.project.mapper;

import com.example.project.dto.product.ProductRequestDTO;
import com.example.project.dto.product.ProductResponseDTO;
import com.example.project.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {

    ProductResponseDTO toProductResponseDTO(Product product);

    Product toProduct(ProductRequestDTO productRequestDTO);
}
