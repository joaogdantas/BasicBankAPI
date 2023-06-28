package com.potig.joaogdantas.BasicBankAPI.domain.customer.model;

import com.potig.joaogdantas.BasicBankAPI.domain.account.model.Account;
import com.potig.joaogdantas.BasicBankAPI.domain.address.model.Address;
import javax.persistence.*;
import lombok.*;

@Table(name = "customers")
@Entity(name = "customer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "documentNumber")
public class Customer {
    @Column(name = "name")
    private String name;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "postal_code")
    private Address address;
    @Id
    @Column(name = "document_number")
    private String documentNumber;
    @Column(name = "age")
    private Integer age;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "account_number")
    private Account account;
}
