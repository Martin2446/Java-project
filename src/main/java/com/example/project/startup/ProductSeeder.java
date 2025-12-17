package com.example.project.startup;

import com.example.project.model.Product;
import com.example.project.repository.OrderRepository;
import com.example.project.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Profile("dev")
@Order(1)
@Component
@RequiredArgsConstructor
public class ProductSeeder implements CommandLineRunner{

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    @Override
    public void run(String... args) throws Exception
    {
        Product product1 = Product.builder()
                .name("Dress")
                .price(45.00)
                .build();

        productRepository.save(product1);

        System.out.println("1 product was seeded!");
    }
}
