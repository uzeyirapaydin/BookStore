package com.uzeyirapaydin.CaseStudy.controller;

import com.uzeyirapaydin.CaseStudy.dto.CustomerDTO;
import com.uzeyirapaydin.CaseStudy.dto.OrderDTO;
import com.uzeyirapaydin.CaseStudy.dto.request.DateIntervalRequest;
import com.uzeyirapaydin.CaseStudy.dto.request.PaginationRequest;
import com.uzeyirapaydin.CaseStudy.service.CustomerService;
import com.uzeyirapaydin.CaseStudy.service.OrderService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("api/order")
@RequiredArgsConstructor
public class OrderController {
    final OrderService orderService;
    final CustomerService customerService;
    @ApiOperation(value = "Create New Order")
    @PostMapping(consumes = "application/json")
    public ResponseEntity<OrderDTO> create(@RequestBody @Valid OrderDTO orderDTO) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        CustomerDTO customerDTO = customerService.getByEmail(email).get();
        return new ResponseEntity<>(orderService.create(orderDTO, customerDTO), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get Order by OrderId")
    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getById(
            @PathVariable String id) {
        return ResponseEntity.ok(orderService.getById(UUID.fromString(id)));
    }

    @ApiOperation(value = "Order Filter By Creation Date Interval")
    @GetMapping("/search")
    public ResponseEntity<Page<OrderDTO>> filterByCreationDate(
            @Valid DateIntervalRequest dateIntervalRequest,
            @Valid PaginationRequest paginationRequest) {
        return ResponseEntity.ok(orderService.getAllByCreationDateBetween(dateIntervalRequest, paginationRequest));
    }
}
