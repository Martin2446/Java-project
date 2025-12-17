package com.example.project.service;

import com.example.project.dto.order.OrderRequestDTO;
import com.example.project.mapper.OrderMapper;
import com.example.project.model.Order;
import com.example.project.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;

    @Transactional
    public Order addOrder(OrderRequestDTO orderRequestDTO)
    {
        Order order = orderMapper.toOrder(orderRequestDTO);
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders()
    {
        return orderRepository.findAll();
    }

    public Order getOrderByAddressOrCreate(String address)
    {
        Optional<Order> fetchedAddress = orderRepository.findByAddress(address);
        return fetchedAddress.orElseGet(() -> orderRepository.save(new Order()));
    }
}
