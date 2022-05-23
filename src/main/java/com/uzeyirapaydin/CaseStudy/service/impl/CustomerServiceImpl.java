package com.uzeyirapaydin.CaseStudy.service.impl;

import com.uzeyirapaydin.CaseStudy.dto.CustomerDTO;
import com.uzeyirapaydin.CaseStudy.entity.Customer;
import com.uzeyirapaydin.CaseStudy.mapper.CustomerEntityMapper;
import com.uzeyirapaydin.CaseStudy.repository.CustomerRepository;
import com.uzeyirapaydin.CaseStudy.exception.CustomerAlreadyExistsException;
import com.uzeyirapaydin.CaseStudy.exception.CustomerNotFoundException;
import com.uzeyirapaydin.CaseStudy.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    final CustomerEntityMapper entityMapper;
    final CustomerRepository repository;

    final PasswordEncoder passwordEncoder;

    @Override
    public CustomerDTO create(CustomerDTO customerDTO) {

        Customer customer = entityMapper.toEntity(customerDTO);

        customer.setId(UUID.randomUUID());
        customer.setPassword(passwordEncoder.encode(customerDTO.getPassword()));

        // Check if email is already used before! Then save if possible.
        getByEmail(customer.getEmail())
                .ifPresentOrElse(customerDTO1 -> {
                    throw new CustomerAlreadyExistsException();
                }, () -> repository.save(customer));
        return entityMapper.toDTO(customer);
    }

    @Override
    public CustomerDTO getById(UUID id) {
        return repository
                .findById(id)
                .map(entityMapper::toDTO)
                .orElseThrow(CustomerNotFoundException::new);

    }

    @Override
    public Optional<CustomerDTO> getByEmail(String email) {
        return repository
                .findByEmail(email).map(entityMapper::toDTO);
    }
}
