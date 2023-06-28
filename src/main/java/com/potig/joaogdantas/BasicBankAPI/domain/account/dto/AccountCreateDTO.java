package com.potig.joaogdantas.BasicBankAPI.domain.account.dto;

public record AccountCreateDTO(
        Integer agencyCode,
        Float balance
) {
}
