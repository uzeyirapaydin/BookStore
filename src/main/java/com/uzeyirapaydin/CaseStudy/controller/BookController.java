package com.uzeyirapaydin.CaseStudy.controller;


import com.uzeyirapaydin.CaseStudy.dto.BookDTO;
import com.uzeyirapaydin.CaseStudy.exception.CustomerAlreadyExistsException;
import com.uzeyirapaydin.CaseStudy.service.BookService;
import com.uzeyirapaydin.CaseStudy.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final CustomerService customerService;
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create new book")
    public ResponseEntity createBook(@RequestBody @Valid BookDTO bookDto) {
        bookService.create(bookDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "Update book stock")
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateStock(@PathVariable(name = "id") UUID id, @RequestBody @NotNull Integer quantity) {
        BookDTO book = bookService.getById(id);
        bookService.updateStock(book,quantity);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "Get all books in the catalog")
    @GetMapping()
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        List<BookDTO> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }
}
