package com.binark.mercato.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotFoundException extends MercatoApiException {
    public NotFoundException(String message) {
        super("NOT FOUND", message);
    }

    public NotFoundException(String message, Throwable cause) {
        super("NOT FOUND", message, cause);
    }
}
