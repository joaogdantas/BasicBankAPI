package com.potig.joaogdantas.BasicBankAPI.domain.creditCard.repository;

import com.potig.joaogdantas.BasicBankAPI.domain.creditCard.model.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CreditCardRepository extends JpaRepository<CreditCard, String> {
    @Query("SELECT c FROM credit_card c WHERE c.cardNumber = :cardNumber")
    Optional<CreditCard> findByCardNumber(@Param("cardNumber") String cardNumber);
    @Modifying
    @Query("DELETE FROM credit_card c WHERE c.cardNumber = :cardNumber")
    void deleteByCardNumber(@Param("cardNumber") String cardNumber);
}
