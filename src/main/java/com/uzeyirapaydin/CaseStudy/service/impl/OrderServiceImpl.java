package com.uzeyirapaydin.CaseStudy.service.impl;

import com.uzeyirapaydin.CaseStudy.dto.constant.OrderStatus;
import com.uzeyirapaydin.CaseStudy.dto.CustomerDTO;
import com.uzeyirapaydin.CaseStudy.dto.OrderDTO;
import com.uzeyirapaydin.CaseStudy.entity.Order;
import com.uzeyirapaydin.CaseStudy.mapper.OrderEntityMapper;
import com.uzeyirapaydin.CaseStudy.repository.OrderRepository;
import com.uzeyirapaydin.CaseStudy.dto.request.DateIntervalRequest;
import com.uzeyirapaydin.CaseStudy.dto.request.PaginationRequest;
import com.uzeyirapaydin.CaseStudy.exception.OrderNotFoundException;
import com.uzeyirapaydin.CaseStudy.service.OrderDetailService;
import com.uzeyirapaydin.CaseStudy.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    final OrderRepository repository;
    final OrderEntityMapper mapper;

    final OrderDetailService orderDetailService;

    @Override
    public OrderDTO getById(UUID id) {
        return repository.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(OrderNotFoundException::new);
    }

    @Override
    public Page<OrderDTO> getAllByCustomerId(UUID customerId, PaginationRequest paginationRequest) {
        return repository.findAllByCustomerId(customerId,
                        PageRequest.of(paginationRequest.getPage(), paginationRequest.getSize()))
                .map(mapper::toDTO);
    }

    @Override
    public Page<OrderDTO> getAllByCreationDateBetween(DateIntervalRequest dateIntervalRequest, PaginationRequest paginationRequest) {
        return repository.findAllByCreationDateBetween(dateIntervalRequest.getStartDate(), dateIntervalRequest.getEndDate(),
                        PageRequest.of(paginationRequest.getPage(), paginationRequest.getSize()))
                .map(mapper::toDTO);
    }

    @Transactional
    @Override
    public OrderDTO create(OrderDTO orderDTO, CustomerDTO customerDTO) {
        Order order = mapper.toEntity(orderDTO);
        orderDTO.getDetails().forEach(orderDetailService::create);
        populateOrderModel(order, customerDTO, OrderStatus.INITIALIZED);

        return mapper.toDTO(repository.save(order));
    }

    private void populateOrderModel(Order order, CustomerDTO customerDTO, String status) {
        order.setId(UUID.randomUUID());
        order.setCustomerId(customerDTO.getId());
        order.setStatus(status);
        order.setCreationDate(new Date());
    }
}
