package com.potig.joaogdantas.BasicBankAPI.service;
import com.potig.joaogdantas.BasicBankAPI.domain.exception.InvalidCreditCardFormatException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class ExpirationDateValidator implements ConstraintValidator<ValidExpirationDate, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return true;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");

        try {
            YearMonth expirationDate = YearMonth.parse(value, formatter);

            int currentYear = YearMonth.now().getYear();
            int currentMonth = YearMonth.now().getMonthValue();

            int year = expirationDate.getYear();
            int month = expirationDate.getMonthValue();

            return year >= currentYear && year >= 2023 && year <= 2099 && month >= 1 && month <= 12;
        } catch (Exception e) {
            throw new InvalidCreditCardFormatException("Data de expiração do cartão incorreta, por favor verifique novamente.");
        }
    }
}

