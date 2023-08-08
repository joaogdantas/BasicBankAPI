package com.potig.joaogdantas.BasicBankAPI.service;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ExpirationDateValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidExpirationDate {
    String message() default "Data de expiração inválida";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}