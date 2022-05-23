package com.uzeyirapaydin.CaseStudy.exception;

import org.springframework.http.HttpStatus;

import java.io.Serial;

public class NotEnoughStockException extends BusinessException {

    @Serial
    private static final long serialVersionUID = 4625551009155669249L;

    private static final String CODE = "1006";
    private static final String DESCRIPTION = "There is no enough stock to order.";
    public NotEnoughStockException() {
        super(DESCRIPTION);
    }
    @Override
    public String getCode() {
        return CODE;
    }

    @Override
    public String getMessage() {
        return DESCRIPTION;
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
