package com.uzeyirapaydin.CaseStudy.controller;

import com.uzeyirapaydin.CaseStudy.dto.CustomerDTO;
import com.uzeyirapaydin.CaseStudy.dto.OrderDTO;
import com.uzeyirapaydin.CaseStudy.dto.request.LoginRequest;
import com.uzeyirapaydin.CaseStudy.dto.request.PaginationRequest;
import com.uzeyirapaydin.CaseStudy.dto.response.JWTAuthResponse;
import com.uzeyirapaydin.CaseStudy.security.JwtTokenProvider;
import com.uzeyirapaydin.CaseStudy.service.CustomerService;
import com.uzeyirapaydin.CaseStudy.service.OrderService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("api/customer")
@RequiredArgsConstructor
public class CustomerController   {

    final CustomerService customerService;
    final OrderService orderService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @ApiOperation(value = "Get Customer By Customer Id")
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getById(
            @PathVariable String id) {
        return ResponseEntity.ok(customerService.getById(UUID.fromString(id)));
    }

    @ApiOperation(value = "Get Current Users Orders")
    @GetMapping("/orders")
    public ResponseEntity<Page<OrderDTO>> getAllOrders(@Valid PaginationRequest paginationRequest) {

        String email =  SecurityContextHolder.getContext().getAuthentication().getName();
        CustomerDTO customerDTO = customerService.getByEmail(email).get();
        return ResponseEntity.ok(orderService.getAllByCustomerId(customerDTO.getId(), paginationRequest));
    }

    @ApiOperation(value = "Register Customer to Book Online Retail app")
    @RequestMapping(value = "signup", method = RequestMethod.POST)
    public ResponseEntity<CustomerDTO> create(@RequestBody CustomerDTO customerDTO) {
        return new ResponseEntity<>(customerService.create(customerDTO), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Login to Book Online Retail app")
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ResponseEntity<JWTAuthResponse> authenticateUser(@RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.generateJwtToken(loginRequest.getEmail());

        return ResponseEntity.ok(new JWTAuthResponse(token));
    }
}
