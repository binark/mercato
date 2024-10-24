package com.binark.mercato.infrastructure.controller.handler;

import com.binark.mercato.domain.dto.output.RequestError;
import com.binark.mercato.exception.InvalidInputException;
import com.binark.mercato.exception.MercatoApiException;
import com.binark.mercato.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

/**
 * The global exception handler, handles all exceptions from controllers
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    public static final String REQUEST_ERROR = "request error ";

    /**
     * Handle request input validation failure
     *
     * @param mae {@link MethodArgumentNotValidException} The thrown exception
     * @return {@link RequestError} error data will all errors messages
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public RequestError handleRequestInputValidationFailure(MethodArgumentNotValidException mae) {
        log.error(REQUEST_ERROR, mae);
        StringBuilder errors = new StringBuilder();
        mae.getBindingResult().getAllErrors().forEach(error -> errors.append("; ").append(error.getDefaultMessage()));
        String message = errors.toString().replaceFirst("; ", "");
        return new RequestError("INVALID REQUEST INPUT", message);
    }

    /**
     * Handle resource not found exception
     *
     * @param e {@link Exception} The thrown exception
     * @return {@link RequestError} error data
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = {HttpRequestMethodNotSupportedException.class, NoResourceFoundException.class})
    public RequestError handleResourceNotFoundException(Exception e) {
        log.error(REQUEST_ERROR, e);
        return new RequestError("NOT FOUND", "Resource not found");
    }

    /**
     * Handle common mercato api exceptions
     *
     * @param mae {@link MercatoApiException} The thrown exception
     * @return {@link RequestError} error data
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {InvalidInputException.class, NotFoundException.class})
    public RequestError handleResourceNotFoundException(MercatoApiException mae) {
        log.error(REQUEST_ERROR, mae);
        return new RequestError(mae.getCode(), mae.getMessage());
    }

    /**
     * Handle generic exception
     *
     * @param ex The thrown exception
     * @return {@link RequestError} The data with generic error message
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public RequestError handleGenericError(Exception ex) {
        log.error(REQUEST_ERROR, ex);
        return new RequestError("INTERNAL SERVER ERROR", "Internal server error");
    }
}
