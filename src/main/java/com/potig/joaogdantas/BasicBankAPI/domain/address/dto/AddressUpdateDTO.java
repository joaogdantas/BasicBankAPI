package com.potig.joaogdantas.BasicBankAPI.domain.address.dto;

public record AddressUpdateDTO(
        String line_1,
        String line_2,
        String city,
        String country
) {
}
