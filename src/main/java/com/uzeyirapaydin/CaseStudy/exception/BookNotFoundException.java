package com.uzeyirapaydin.CaseStudy.exception;

import org.springframework.http.HttpStatus;

import java.io.Serial;

public class BookNotFoundException extends BusinessException {

    @Serial
    private static final long serialVersionUID = 972780754326610913L;

    private static final String CODE = "1004";
    private static final String DESCRIPTION = "Book cannot be founded.";

    public BookNotFoundException() {
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
        return HttpStatus.NOT_FOUND;
    }
}
