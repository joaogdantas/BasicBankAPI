package com.potig.joaogdantas.BasicBankAPI.domain.address.dto;

public record AddressCreateDTO(
        String line_1,
        String line_2,
        String postalCode,
        String city,
        String country
) {
}
