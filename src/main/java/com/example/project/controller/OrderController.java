package com.example.project.controller;

import com.example.project.dto.order.OrderRequestDTO;
import com.example.project.dto.order.OrderResponseDTO;
import com.example.project.mapper.OrderMapper;
import com.example.project.model.Order;
import com.example.project.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@Validated
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @PostMapping
    public ResponseEntity<OrderResponseDTO> addOrder(@Valid @RequestBody OrderRequestDTO orderRequestDTO)
    {
        Order addOrder = orderService.addOrder(orderRequestDTO);
        return new ResponseEntity<>(orderMapper.toOrderResponseDTO(addOrder), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> getAllOrders()
    {
        return new ResponseEntity<>(orderMapper.toOrderResponseDTOList(orderService.getAllOrders()), HttpStatus.OK);
    }
}
