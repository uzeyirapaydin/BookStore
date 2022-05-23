package com.uzeyirapaydin.CaseStudy.repository;

import com.uzeyirapaydin.CaseStudy.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, UUID> {

    Optional<Customer> findByEmail(String email);
}
