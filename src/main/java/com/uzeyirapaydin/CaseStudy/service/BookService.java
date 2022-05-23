package com.uzeyirapaydin.CaseStudy.service;

import com.uzeyirapaydin.CaseStudy.dto.BookDTO;
import com.uzeyirapaydin.CaseStudy.dto.CustomerDTO;
import com.uzeyirapaydin.CaseStudy.dto.request.PaginationRequest;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface BookService {

    BookDTO getById(UUID id);

    List<BookDTO> getAllBooks();

    BookDTO create(BookDTO bookDTO);
    BookDTO updateStock(BookDTO bookDTO, int newStock);

    void checkStockEnough(BookDTO bookDTO, int amount);

    Optional<BookDTO> findByNameAndAuthorName(String name, String authorName);
}
