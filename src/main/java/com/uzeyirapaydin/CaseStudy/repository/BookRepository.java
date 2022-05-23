package com.uzeyirapaydin.CaseStudy.repository;

import com.uzeyirapaydin.CaseStudy.dto.request.PaginationRequest;
import com.uzeyirapaydin.CaseStudy.entity.Book;
import com.uzeyirapaydin.CaseStudy.entity.Customer;
import com.uzeyirapaydin.CaseStudy.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookRepository extends MongoRepository<Book, UUID> {
    Optional<Book> findByNameAndAuthorName(String name, String authorName);
}
