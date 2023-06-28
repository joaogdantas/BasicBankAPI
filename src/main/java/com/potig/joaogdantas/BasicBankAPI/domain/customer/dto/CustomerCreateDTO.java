package com.potig.joaogdantas.BasicBankAPI.domain.customer.dto;

public record CustomerCreateDTO(
        String name,
        String postalCode,
        String documentNumber,
        Integer age,
        Integer accountNumber
) {
}
