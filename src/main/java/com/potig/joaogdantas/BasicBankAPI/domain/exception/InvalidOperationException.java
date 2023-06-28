package com.potig.joaogdantas.BasicBankAPI.domain.exception;

public class InvalidOperationException extends RuntimeException {
    public InvalidOperationException(String message) {
        super(message);
    }
}
