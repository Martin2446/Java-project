package com.example.project.service;

import com.example.project.controller.ProductController;
import com.example.project.dto.product.ProductRequestDTO;
import com.example.project.mapper.ProductMapper;
import com.example.project.model.Product;
import com.example.project.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductController productController;
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    @Transactional
    public Product addProduct(ProductRequestDTO productRequestDTO)
    {
        Product product = productMapper.toProduct(productRequestDTO);
        return productRepository.save(product);
    }
}
