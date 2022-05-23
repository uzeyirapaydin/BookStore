package com.uzeyirapaydin.CaseStudy.exception;

import org.springframework.http.HttpStatus;

import java.io.Serial;

public class BookAlreadyExistsException extends BusinessException {

    @Serial
    private static final long serialVersionUID = 972780754326610913L;

    private static final String CODE = "1005";
    private static final String DESCRIPTION = "Book already exists.";

    public BookAlreadyExistsException() {
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
        return HttpStatus.CONFLICT;
    }
}

