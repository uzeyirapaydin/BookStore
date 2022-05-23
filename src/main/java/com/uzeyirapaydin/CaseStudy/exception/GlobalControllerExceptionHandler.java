package com.uzeyirapaydin.CaseStudy.exception;

import com.uzeyirapaydin.CaseStudy.dto.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Slf4j
@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    @ResponseStatus(value = BAD_REQUEST)
    public ErrorResponse handleSQLIntegrityConstraintViolationException(HttpServletRequest httpServletRequest, SQLIntegrityConstraintViolationException exception) {
        log.warn("ConstraintViolationException", exception);
        String exceptionDetailMessage = exception.getMessage();
        return buildErrorResponse(httpServletRequest, HttpStatus.BAD_REQUEST, exceptionDetailMessage);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = INTERNAL_SERVER_ERROR)
    public ErrorResponse handleGlobalException(HttpServletRequest httpServletRequest, Exception exception) {
        log.error("Exception: ", exception);
        String exceptionDetailMessage = exception.getMessage();
        return buildErrorResponse(httpServletRequest, HttpStatus.INTERNAL_SERVER_ERROR, exceptionDetailMessage);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(value = INTERNAL_SERVER_ERROR)
    public ErrorResponse handleGlobalException(HttpServletRequest httpServletRequest, MethodArgumentTypeMismatchException e) {
        log.error("MethodArgumentTypeMismatchException: ", e);
        String exceptionDetailMessage = e.getMessage();
        return buildErrorResponse(httpServletRequest, HttpStatus.INTERNAL_SERVER_ERROR, exceptionDetailMessage);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorResponse handleMethodArgumentNotValidException(HttpServletRequest httpServletRequest, MethodArgumentNotValidException e) {
        List<String> errors=  e.getBindingResult().getFieldErrors().stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());
        String exceptionDetailMessage = errors.toString();
        return buildErrorResponse(httpServletRequest, HttpStatus.BAD_REQUEST, exceptionDetailMessage);

    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(value = BAD_REQUEST)
    public ErrorResponse handleMissingServletRequestParameterException(HttpServletRequest httpServletRequest,
                                                                       MissingServletRequestParameterException e) {
        log.error("MissingServletRequestParameterException: ", e);
        return buildErrorResponse(httpServletRequest, HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(BookNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorResponse handleBookNotFoundException(HttpServletRequest httpServletRequest, BookNotFoundException e) {
        String exceptionDetailMessage = e.getMessage();
        return buildErrorResponse(httpServletRequest, HttpStatus.NOT_FOUND, exceptionDetailMessage);
    }

    @ExceptionHandler(NotEnoughStockException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorResponse handleNotEnoughStockException(HttpServletRequest httpServletRequest, NotEnoughStockException e) {
        String exceptionDetailMessage = e.getMessage();
        return buildErrorResponse(httpServletRequest, HttpStatus.NOT_FOUND, exceptionDetailMessage);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorResponse handleCustomerNotFoundException(HttpServletRequest httpServletRequest, CustomerNotFoundException e) {
        String exceptionDetailMessage = e.getMessage();
        return buildErrorResponse(httpServletRequest, HttpStatus.NOT_FOUND, exceptionDetailMessage);
    }



    @ExceptionHandler(AuthenticationAPIException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    ErrorResponse handleAuthenticationAPIException(HttpServletRequest httpServletRequest, AuthenticationAPIException e) {
        String exceptionDetailMessage = e.getMessage();
        return buildErrorResponse(httpServletRequest, HttpStatus.UNAUTHORIZED, exceptionDetailMessage);

    }

    @ExceptionHandler(CustomerAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ErrorResponse handleCustomerAlreadyExistsException(HttpServletRequest httpServletRequest, CustomerAlreadyExistsException e) {
        String exceptionDetailMessage = e.getMessage();
        return buildErrorResponse(httpServletRequest, HttpStatus.NOT_FOUND, exceptionDetailMessage);
    }

    @ExceptionHandler(BookAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ErrorResponse handleBookAlreadyExistsException(HttpServletRequest httpServletRequest, BookAlreadyExistsException e) {
        String exceptionDetailMessage = e.getMessage();
        return buildErrorResponse(httpServletRequest, HttpStatus.NOT_FOUND, exceptionDetailMessage);
    }

    @ExceptionHandler(StockModifiedException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ErrorResponse handleStockModifiedException(HttpServletRequest httpServletRequest, StockModifiedException e) {
        String exceptionDetailMessage = e.getMessage();
        return buildErrorResponse(httpServletRequest, HttpStatus.NOT_FOUND, exceptionDetailMessage);
    }

    @ExceptionHandler(StockNegativeValueException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ErrorResponse handleStockNegativeValueException(HttpServletRequest httpServletRequest, StockNegativeValueException e) {
        String exceptionDetailMessage = e.getMessage();
        return buildErrorResponse(httpServletRequest, BAD_REQUEST, exceptionDetailMessage);
    }



    @ExceptionHandler(OrderNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ErrorResponse handlOrderNotFoundException(HttpServletRequest httpServletRequest, OrderNotFoundException e) {
        String exceptionDetailMessage = e.getMessage();
        return buildErrorResponse(httpServletRequest, HttpStatus.NOT_FOUND, exceptionDetailMessage);
    }


    private ErrorResponse buildErrorResponse(HttpServletRequest request, HttpStatus statusType, String exceptionDetailMessage) {
        return ErrorResponse.builder()
                .title(statusType.getReasonPhrase())
                .status(statusType.value())
                .description(exceptionDetailMessage)
                .requestUri(request.getRequestURI())
                .requestMethod(request.getMethod())
                .build();
    }
}
