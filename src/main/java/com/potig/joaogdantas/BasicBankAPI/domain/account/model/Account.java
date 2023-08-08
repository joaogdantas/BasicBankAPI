package com.potig.joaogdantas.BasicBankAPI.domain.account.model;

import com.potig.joaogdantas.BasicBankAPI.domain.creditCard.model.CreditCard;
import com.potig.joaogdantas.BasicBankAPI.domain.customer.model.Customer;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import javax.persistence.*;
import java.util.List;

@Table(name = "accounts")
@Entity(name = "account")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "accountNumber")
public class Account {
    @Column(name = "agency_code")
    private Integer agencyCode;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_number_seq")
    @SequenceGenerator(name = "account_number_seq", sequenceName = "account_number_seq", allocationSize = 1)
    @Column(name = "account_number")
    private Integer accountNumber;
    @Column(name = "balance")
    private Float balance;
    @OneToOne(mappedBy = "account")
    private Customer customer;
    @OneToMany(mappedBy = "account")
    private List<CreditCard> creditCards;

    public ResponseEntity deposit(Float amount){
        balance += amount;
        return ResponseEntity.status(HttpStatus.OK).body("Valor depositado com sucesso!");
    }
    public ResponseEntity withdraw(Float amount){
        if(balance < amount){
            return ResponseEntity.badRequest().body("Saldo insuficiente, por favor digite um valor válido.");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Valor retirado com sucesso!");
    }
}
