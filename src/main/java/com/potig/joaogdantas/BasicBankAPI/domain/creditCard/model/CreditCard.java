package com.potig.joaogdantas.BasicBankAPI.domain.creditCard.model;

import com.potig.joaogdantas.BasicBankAPI.domain.account.model.Account;
import com.potig.joaogdantas.BasicBankAPI.domain.creditCard.dto.CreditCardReturnDTO;
import lombok.*;

import javax.persistence.*;

@Entity(name = "credit_card")
@Table(name = "credit_cards")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "cardNumber")
public class CreditCard {
    @Id
    @Column(name = "card_number")
    private String cardNumber;
    @Column(name = "card_brand")
    private String cardBrand;
    @Column(name = "expiration_date")
    private String expirationDate;
    @Column(name = "cvv")
    private String cvv;
    @Column(name = "credit_limit")
    private Float creditLimit;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public String getMaskedCreditCardNumber(){
        String lastFourDigits = cardNumber.substring(cardNumber.length() - 4);
        String maskedDigits = cardNumber.substring(0, cardNumber.length() - 4).replaceAll("\\d", "*");

        return maskedDigits + lastFourDigits;
    }

    public CreditCardReturnDTO toCreditCardReturnDTO() {
        String maskedCardNumber = getMaskedCreditCardNumber();

        return new CreditCardReturnDTO(
                maskedCardNumber,
                cardBrand
        );
    }
}
