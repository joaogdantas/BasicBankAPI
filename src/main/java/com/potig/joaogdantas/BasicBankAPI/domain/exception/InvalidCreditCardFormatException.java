package com.potig.joaogdantas.BasicBankAPI.domain.exception;

public class InvalidCreditCardFormatException extends RuntimeException {
    public InvalidCreditCardFormatException(String message) {
        super(message);
    }

}
