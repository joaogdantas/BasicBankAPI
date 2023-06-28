package com.potig.joaogdantas.BasicBankAPI.domain.address.repository;

import com.potig.joaogdantas.BasicBankAPI.domain.account.model.Account;
import com.potig.joaogdantas.BasicBankAPI.domain.address.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, String> {
    @Query("SELECT a FROM address a WHERE a.postalCode = :postalCode")
    Optional<Address> findByPostalCode(@Param("postalCode") String postalCode);
    @Modifying
    @Query("DELETE FROM address a WHERE a.postalCode = :postalCode")
    void deleteByPostalCode(@Param("postalCode") String postalCode);
}
