package com.potig.joaogdantas.BasicBankAPI.domain.account.dto;

import com.potig.joaogdantas.BasicBankAPI.domain.account.model.Account;
import com.potig.joaogdantas.BasicBankAPI.domain.address.model.Address;

public record AccountReturnDTO(
        Integer agencyCode,
        Integer accountNumber,
        Float balance
) {
    public AccountReturnDTO(Account account){
        this(
                account.getAgencyCode(),
                account.getAccountNumber(),
                account.getBalance()
        );
    }
}
