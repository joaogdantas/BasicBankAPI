package com.potig.joaogdantas.BasicBankAPI.domain.address.model;

import javax.persistence.*;

import com.potig.joaogdantas.BasicBankAPI.domain.customer.model.Customer;
import lombok.*;

@Table(name = "address")
@Entity(name = "address")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "postalCode")
public class Address {
    @Column(name = "line_1")
    private String line1;
    @Column(name = "line_2")
    private String line2;
    @Id
    @Column(name = "postal_code")
    private String postalCode;
    @Column(name = "city")
    private String city;
    @Column(name = "country")
    private String country;
    @OneToOne(mappedBy = "address")
    private Customer customer;
}
