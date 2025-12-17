package com.example.project.mapper;

import com.example.project.dto.order.OrderRequestDTO;
import com.example.project.dto.order.OrderResponseDTO;
import com.example.project.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {

    OrderResponseDTO toOrderResponseDTO(Order order);

    List<OrderResponseDTO> toOrderResponseDTOList(List<Order> orders);

    @Mapping(target = "orderId", ignore = true)
    @Mapping(target = "products", ignore = true)
    Order toOrder(OrderRequestDTO orderRequestDTO);
}
