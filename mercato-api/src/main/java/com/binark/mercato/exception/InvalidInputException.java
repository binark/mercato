package com.binark.mercato.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvalidInputException extends MercatoApiException {
    public InvalidInputException(String message) {
        super("BAD REQUEST", message);
    }

    public InvalidInputException(String message, Throwable cause) {
        super("BAD REQUEST", message, cause);
    }
}
