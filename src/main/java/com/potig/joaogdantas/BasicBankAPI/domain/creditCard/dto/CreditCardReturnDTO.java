package com.potig.joaogdantas.BasicBankAPI.domain.creditCard.dto;

public record CreditCardReturnDTO(
        String maskedNumber,
        String cardBrand
) {
}
