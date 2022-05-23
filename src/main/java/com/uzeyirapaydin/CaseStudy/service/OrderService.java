package com.uzeyirapaydin.CaseStudy.service;

import com.uzeyirapaydin.CaseStudy.dto.CustomerDTO;
import com.uzeyirapaydin.CaseStudy.dto.OrderDTO;
import com.uzeyirapaydin.CaseStudy.dto.request.DateIntervalRequest;
import com.uzeyirapaydin.CaseStudy.dto.request.PaginationRequest;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface OrderService {

    OrderDTO getById(UUID id);

    Page<OrderDTO> getAllByCustomerId(UUID customerId, PaginationRequest paginationRequest);
    Page<OrderDTO> getAllByCreationDateBetween(DateIntervalRequest dateIntervalRequest, PaginationRequest paginationRequest);

    OrderDTO create(OrderDTO orderDTO, CustomerDTO customerDTO);
}
