package com.potig.joaogdantas.BasicBankAPI.domain.customer.repository;

import com.potig.joaogdantas.BasicBankAPI.domain.customer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, String> {
    @Query("SELECT c FROM customer c WHERE c.documentNumber = :documentNumber")
    Optional<Customer> findByDocument(@Param("documentNumber") String documentNumber);
    @Modifying
    @Query("DELETE FROM customer c WHERE c.documentNumber = :documentNumber")
    void deleteByDocumentNumber(@Param("documentNumber") String documentNumber);
}
