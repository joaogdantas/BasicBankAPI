package com.potig.joaogdantas.BasicBankAPI.domain.creditCard.dto;

public record CreditCardCreateDTO(
        String cardNumber,
        String cardBrand,
        String expirationDate,
        String cvv,
        Float creditLimit,
        Integer accountNumber
) {
}
