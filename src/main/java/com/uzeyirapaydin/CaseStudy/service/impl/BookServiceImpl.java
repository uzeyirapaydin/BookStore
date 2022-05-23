package com.uzeyirapaydin.CaseStudy.service.impl;

import com.uzeyirapaydin.CaseStudy.dto.BookDTO;
import com.uzeyirapaydin.CaseStudy.dto.request.PaginationRequest;
import com.uzeyirapaydin.CaseStudy.entity.Book;
import com.uzeyirapaydin.CaseStudy.exception.*;
import com.uzeyirapaydin.CaseStudy.mapper.BookEntityMapper;
import com.uzeyirapaydin.CaseStudy.repository.BookRepository;
import com.uzeyirapaydin.CaseStudy.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    final BookRepository repository;
    final BookEntityMapper mapper;

    @Override
    public BookDTO getById(UUID id) {
        return repository.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(BookNotFoundException::new);
    }

    @Override
    public List<BookDTO> getAllBooks() {
        return repository.findAll().stream()
                .map(mapper::toDTO).toList();
    }

    @Override
    public BookDTO create(BookDTO bookDTO) {

        Book book = mapper.toEntity(bookDTO);

        book.setId(UUID.randomUUID());

        findByNameAndAuthorName(book.getName(), book.getAuthorName())
                .ifPresentOrElse(customerDTO1 -> {
                    throw new BookAlreadyExistsException();
                }, () -> repository.save(book));

        return mapper.toDTO(repository.save(book));
    }

    @Override
    public BookDTO updateStock(BookDTO bookDTO, int newStock) throws StockModifiedException {
        if (newStock < 0) {
            throw new StockNegativeValueException();
        }
        Book book = mapper.toEntity(bookDTO);

        book.setStock(newStock);

        try {
            return mapper.toDTO(repository.save(book));
        } catch (OptimisticLockingFailureException e) {
            throw new StockModifiedException();
        }
    }

    @Override
    public void checkStockEnough(BookDTO bookDTO, int amount) {
        if (bookDTO.getStock() < amount) {
            throw new NotEnoughStockException();
        }
    }

    @Override
    public Optional<BookDTO> findByNameAndAuthorName(String name, String authorName) {
        return repository
                .findByNameAndAuthorName(name, authorName).map(mapper::toDTO);
    }
}
