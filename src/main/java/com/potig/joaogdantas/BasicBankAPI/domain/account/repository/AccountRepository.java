package com.potig.joaogdantas.BasicBankAPI.domain.account.repository;

import com.potig.joaogdantas.BasicBankAPI.domain.account.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    @Query("SELECT a FROM account a WHERE a.accountNumber = :accountNumber")
    Optional<Account> findByAccountNumber(@Param("accountNumber") Integer accountNumber);
    @Modifying
    @Query("DELETE FROM account a WHERE a.accountNumber = :accountNumber")
    void deleteByAccountNumber(@Param("accountNumber") Integer accountNumber);
}
