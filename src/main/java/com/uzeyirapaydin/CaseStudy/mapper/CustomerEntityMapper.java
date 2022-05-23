package com.uzeyirapaydin.CaseStudy.mapper;

import com.uzeyirapaydin.CaseStudy.dto.CustomerDTO;
import com.uzeyirapaydin.CaseStudy.entity.Customer;
import org.springframework.stereotype.Component;

@Component("mapper-customer")
public class CustomerEntityMapper implements EntityMapper<Customer, CustomerDTO> {

    @Override
    public CustomerDTO toDTO(Customer customer) {
        return CustomerDTO.builder()
                .id(customer.getId())
                .name(customer.getName())
                .surname(customer.getSurname())
                .email(customer.getEmail())
                .build();
    }

    @Override
    public Customer toEntity(CustomerDTO customerDTO) {
        return Customer.builder()
                .name(customerDTO.getName())
                .surname(customerDTO.getSurname())
                .email(customerDTO.getEmail())
                .password(customerDTO.getPassword())
                .build();
    }
}
