package com.uzeyirapaydin.CaseStudy.exception;

import org.springframework.core.NestedRuntimeException;
import org.springframework.http.HttpStatus;

import java.io.Serial;

public abstract class BusinessException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 2233491462684998597L;

    public BusinessException(String msg) {
        super(msg);
    }

    public abstract String getCode();

    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
