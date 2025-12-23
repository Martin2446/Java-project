package com.example.project.service;

import com.example.project.dto.product.ProductRequestDTO;
import com.example.project.mapper.ProductMapper;
import com.example.project.model.Order;
import com.example.project.model.Product;
import com.example.project.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final OrderService orderService;

    @Transactional
    public Product saveProduct(ProductRequestDTO productRequestDTO)
    {
        Product product = productMapper.toProduct(productRequestDTO);

        return productRepository.save(product);
    }

    public List<Product> getAllProducts()
    {
        return productRepository.findAll();
    }

    @Transactional
    public void deleteProduct(Long id)
    {
        if(productRepository.existsById(id))
            productRepository.deleteById(id);
        else
            throw new EntityNotFoundException("Product with id " + id + " does not  exist!");
    }

    public Product getProductByIdOrThrow(Long id)
    {
        return productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product with id " + id + " does not exist!"));
    }

    @Transactional
    public Product addProductToOrder(@Positive Long productId, String orderAddress)
    {
        Product product = getProductByIdOrThrow(productId);
        Order order = orderService.getOrderByAddressOrThrow(orderAddress);

        product.getOrders().add(order);
        order.getProducts().add(product);

        return productRepository.save(product);
    }
}
