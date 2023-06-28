package com.potig.joaogdantas.BasicBankAPI.domain.customer.dto;

import com.potig.joaogdantas.BasicBankAPI.domain.account.model.Account;
import com.potig.joaogdantas.BasicBankAPI.domain.address.model.Address;
import com.potig.joaogdantas.BasicBankAPI.domain.customer.model.Customer;
import com.sun.istack.NotNull;

public record CustomerReturnDTO(
        String name,
        String documentNumber,
        Integer age
) {
    public CustomerReturnDTO(Customer customer){
        this(
                customer.getName(),
                customer.getDocumentNumber(),
                customer.getAge()
        );
    }
}
