package com.example.project.controller;

import com.example.project.dto.product.ProductRequestDTO;
import com.example.project.dto.product.ProductResponseDTO;
import com.example.project.mapper.ProductMapper;
import com.example.project.model.Product;
import com.example.project.service.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Validated
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @PostMapping
    public ResponseEntity<ProductResponseDTO> addProduct(@Valid @RequestBody ProductRequestDTO productRequestDTO)
    {
        Product addProduct = productService.addProduct(productRequestDTO);
        return new ResponseEntity<>(productMapper.toProductResponseDTO(addProduct), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts()
    {
        return new ResponseEntity<>(productMapper.toProductResponseDTOList(productService.getAllProducts()), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable @Positive Long id)
    {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
