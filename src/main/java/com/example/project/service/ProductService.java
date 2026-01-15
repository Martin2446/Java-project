package com.example.project.service;

import com.example.project.dto.product.ProductRequestDTO;
import com.example.project.mapper.ProductMapper;
import com.example.project.model.Order;
import com.example.project.model.OrderStatus;
import com.example.project.model.Product;
import com.example.project.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    @Transactional
    public Product updateProduct(Long id, ProductRequestDTO dto)
    {
        Product fetchedProduct = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found"));

        fetchedProduct.setPrice(dto.price());
        fetchedProduct.setName(dto.name());
        fetchedProduct.setAmount(dto.amount());

        return productRepository.save(fetchedProduct);
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

        if (product.getAmount() <= 0) {
            throw new IllegalStateException("Stock insufficient! Item '" + product.getName() + "' is out of stock.");
        }

        long countInOrder = order.getProducts().stream()
                .filter(p -> p.getId().equals(productId))
                .count();

        if (countInOrder + 1 > product.getAmount()) {
            throw new IllegalStateException("Cannot add more! You already have " + countInOrder +
                    " in your cart, and we only have " + product.getAmount() + " in stock.");
        }

        product.getOrders().add(order);
        order.getProducts().add(product);

        return productRepository.save(product);
    }

    @Transactional
    public void reduceStockForOrder(String orderAddress) {
        Order order = orderService.getOrderByAddressOrThrow(orderAddress);

        if (order.getStatus() == OrderStatus.COMPLETED) {
            throw new IllegalStateException("Order is already processed and stock has been reduced.");
        }

        List<Product> productsInOrder = order.getProducts();

        for (Product product : productsInOrder) {
            if (product.getAmount() > 0) {
                product.setAmount(product.getAmount() - 1);

                productRepository.save(product);
            } else {
                throw new IllegalStateException("Cannot complete order: " + product.getName() + " is out of stock!");
            }
        }

        order.setStatus(OrderStatus.COMPLETED);
    }

}
