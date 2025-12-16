package com.example.project.mapper;

import com.example.project.dto.product.ProductRequestDTO;
import com.example.project.dto.product.ProductResponseDTO;
import com.example.project.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {

    ProductResponseDTO toProductResponseDTO(Product product);

    List<ProductResponseDTO> toProductResponseDTOList(List<Product> products);

    @Mapping(target = "id", ignore = true)
    Product toProduct(ProductRequestDTO productRequestDTO);
}
