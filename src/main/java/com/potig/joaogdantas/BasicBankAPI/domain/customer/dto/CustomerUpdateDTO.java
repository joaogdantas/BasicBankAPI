package com.potig.joaogdantas.BasicBankAPI.domain.customer.dto;

import com.potig.joaogdantas.BasicBankAPI.domain.account.model.Account;
import com.sun.istack.NotNull;
import io.swagger.models.auth.In;

public record CustomerUpdateDTO(
        String name,
        Integer age
) {
}
