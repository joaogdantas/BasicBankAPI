package com.potig.joaogdantas.BasicBankAPI.controller;

import com.potig.joaogdantas.BasicBankAPI.domain.account.dto.AccountCreateDTO;
import com.potig.joaogdantas.BasicBankAPI.domain.account.dto.AccountOperationDTO;
import com.potig.joaogdantas.BasicBankAPI.domain.account.dto.AccountUpdateAgencyDTO;
import com.potig.joaogdantas.BasicBankAPI.domain.account.model.Account;
import com.potig.joaogdantas.BasicBankAPI.domain.account.repository.AccountRepository;
import com.potig.joaogdantas.BasicBankAPI.domain.account.dto.AccountReturnDTO;
import com.potig.joaogdantas.BasicBankAPI.domain.exception.InvalidOperationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    private AccountRepository repository;
    private Account account;

    @PostMapping
    public ResponseEntity createAccount(@RequestBody AccountCreateDTO accountCreateDTO){
        Account account = new Account();

        account.setAgencyCode(accountCreateDTO.agencyCode());
        account.setBalance(accountCreateDTO.balance());

        Account savedAccount = repository.save(account);
        return ResponseEntity.status(HttpStatus.CREATED).body("Conta criada com sucesso!");
    }
    @GetMapping("/{accountNumber}")
    public ResponseEntity getById(@PathVariable Integer accountNumber) {
        Optional<Account> optionalAccount = repository.findByAccountNumber(accountNumber);

        return optionalAccount.map(account -> ResponseEntity.ok(new AccountReturnDTO(account)))
                    .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/all")
    public ResponseEntity<List<AccountReturnDTO>> findAllAccounts(){
        var accounts = repository.findAll();
        List<AccountReturnDTO> result = new ArrayList<>();

        accounts.forEach(a -> result.add(new AccountReturnDTO(a.getAgencyCode(), a.getAccountNumber(), a.getBalance())));

        return ResponseEntity.ok(result);
    }
    @PutMapping("/updateAgency/{accountNumber}")
    public ResponseEntity updateAgency(@PathVariable Integer accountNumber, @RequestBody AccountUpdateAgencyDTO accountUpdateAgencyDTO) {
        Optional<Account> optionalAccount = repository.findByAccountNumber(accountNumber);

        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            account.setAgencyCode(accountUpdateAgencyDTO.agencyCode());

            Account savedAccount = repository.save(account);

            return ResponseEntity.status(HttpStatus.OK).body("Agência atualizada com sucesso!");
        }

        return ResponseEntity.notFound().build();
    }
    @PutMapping("/{operation}/{accountNumber}")
    public ResponseEntity deposit(@PathVariable String operation, @PathVariable Integer accountNumber, @RequestBody AccountOperationDTO operationData){
        try {
            Float depositAmount = Float.parseFloat(operationData.amount());
            Optional<Account> optionalAccount = repository.findByAccountNumber(accountNumber);

            if (optionalAccount.isPresent()) {
                Account account = optionalAccount.get();

                if(operation.equalsIgnoreCase("deposit")){
                    account.deposit(depositAmount);
                }
                else if(operation.equalsIgnoreCase("withdraw")){
                    account.withdraw(depositAmount);
                }
                else {
                   throw new InvalidOperationException("Operação inválida! Por favor, digite uma operação válida");
                }
                repository.save(account);
                return ResponseEntity.status(HttpStatus.OK).body("Operação realizada com sucesso!");
            }
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("Valor inválido, por favor, digite um valor válido.");
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("{accountNumber}")
    @Transactional
    public ResponseEntity deleteAccount(@PathVariable Integer accountNumber){
        Optional<Account> optionalAccount = repository.findByAccountNumber(accountNumber);

        if(optionalAccount.isPresent()){
            repository.deleteByAccountNumber(accountNumber);
            return ResponseEntity.status(HttpStatus.OK).body("Conta deletada com sucesso!");
        }
        return ResponseEntity.notFound().build();
    }
}
