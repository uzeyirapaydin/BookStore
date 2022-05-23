package com.uzeyirapaydin.CaseStudy.service.impl;

import com.uzeyirapaydin.CaseStudy.dto.BookDTO;
import com.uzeyirapaydin.CaseStudy.entity.OrderDetail;
import com.uzeyirapaydin.CaseStudy.exception.StockModifiedException;
import com.uzeyirapaydin.CaseStudy.service.BookService;
import com.uzeyirapaydin.CaseStudy.service.OrderDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements OrderDetailService {

    final BookService bookService;

    @Retryable(value = StockModifiedException.class, maxAttemptsExpression = "${app.order.retry.max-attempts:3}")
    @Override
    public OrderDetail create(OrderDetail orderDetail) {

        BookDTO bookDTO = bookService.getById(orderDetail.getBookId());

        checkStock(bookDTO, orderDetail);
        decrementAndUpdateStock(bookDTO, orderDetail);

        orderDetail.setItemPrice(bookDTO.getPrice() * orderDetail.getItemQuantity());

        return orderDetail;
    }

    private void checkStock(BookDTO bookDTO, OrderDetail orderDetail) {
        bookService.checkStockEnough(bookDTO, orderDetail.getItemQuantity());
    }

    private void decrementAndUpdateStock(BookDTO bookDTO, OrderDetail orderDetail) {
        bookService.updateStock(bookDTO, bookDTO.getStock() - orderDetail.getItemQuantity());
    }
}
