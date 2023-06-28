package com.potig.joaogdantas.BasicBankAPI.domain.address.dto;

import com.potig.joaogdantas.BasicBankAPI.domain.address.model.Address;

public record AddressReturnDTO(
        String line_1,
        String line_2,
        String postalCode,
        String city,
        String country
) {
    public AddressReturnDTO(Address address){
        this(
                address.getLine1(),
                address.getLine2(),
                address.getPostalCode(),
                address.getCity(),
                address.getCountry()
        );
    }
}
