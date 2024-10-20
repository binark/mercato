package com.binark.mercato.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MercatoApiException extends Exception {
    protected final String code;
    protected final String message;

    public MercatoApiException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public MercatoApiException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.message = message;
    }
}
