package com.uzeyirapaydin.CaseStudy.mapper;

import com.uzeyirapaydin.CaseStudy.dto.OrderDTO;
import com.uzeyirapaydin.CaseStudy.entity.Order;
import org.springframework.stereotype.Component;

@Component("mapper-order")
public class OrderEntityMapper implements EntityMapper<Order, OrderDTO> {

    @Override
    public OrderDTO toDTO(Order order) {
        return OrderDTO.builder()
                .id(order.getId())
                .status(order.getStatus())
                .customerId(order.getCustomerId())
                .creationDate(order.getCreationDate())
                .details(order.getDetails())
                .build();
    }

    @Override
    public Order toEntity(OrderDTO orderDTO) {
        return Order.builder()
                .details(orderDTO.getDetails())
                .build();
    }
}
