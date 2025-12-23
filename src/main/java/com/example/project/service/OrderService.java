package com.example.project.service;

import com.example.project.dto.order.OrderRequestDTO;
import com.example.project.mapper.OrderMapper;
import com.example.project.model.Order;
import com.example.project.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;

    @Transactional
    public Order saveOrder(OrderRequestDTO orderRequestDTO)
    {
        Order order = orderMapper.toOrder(orderRequestDTO);
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders()
    {
        return orderRepository.findAll();
    }

    @Transactional
    public void deleteOrder(@Positive Long id)
    {
        if(orderRepository.existsById(id))
            orderRepository.deleteById(id);
        else
            throw new EntityNotFoundException("Order with id " + id + " does not exist!");
    }

    public Order getOrderByAddressOrThrow(String orderAddress)
    {
        return orderRepository.findByAddress(orderAddress).orElseThrow(() -> new EntityNotFoundException("Order with address " + orderAddress + " does not exist!"));
    }
}
