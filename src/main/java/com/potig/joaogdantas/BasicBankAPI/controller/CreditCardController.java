package com.potig.joaogdantas.BasicBankAPI.controller;

import com.potig.joaogdantas.BasicBankAPI.domain.account.model.Account;
import com.potig.joaogdantas.BasicBankAPI.domain.account.repository.AccountRepository;
import com.potig.joaogdantas.BasicBankAPI.domain.creditCard.model.CreditCard;
import com.potig.joaogdantas.BasicBankAPI.domain.creditCard.repository.CreditCardRepository;
import com.potig.joaogdantas.BasicBankAPI.domain.creditCard.dto.CreditCardCreateDTO;
import com.potig.joaogdantas.BasicBankAPI.domain.creditCard.dto.CreditCardReturnDTO;
import com.potig.joaogdantas.BasicBankAPI.domain.creditCard.dto.CreditCardUpdateLimitDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/creditCard")
public class CreditCardController {
    @Autowired
    private CreditCardRepository creditCardRepository;
    @Autowired
    private AccountRepository accountRepository;
    private Account account;
    private CreditCard creditCard;
    @PostMapping
    public ResponseEntity createCreditCard(@RequestBody CreditCardCreateDTO data){
        CreditCard creditCard = new CreditCard();

        creditCard.setCardNumber(data.cardNumber());
        creditCard.setCardBrand(data.cardBrand());
        creditCard.setExpirationDate(data.expirationDate());
        creditCard.setCvv(data.cvv());
        creditCard.setCreditLimit(data.creditLimit());

        Optional<Account> optionalAccount = accountRepository.findByAccountNumber(data.accountNumber());
        if (optionalAccount.isPresent()) {
            creditCard.setAccount(optionalAccount.get());
        } else {
            return ResponseEntity.badRequest().body("Não existe uma conta com esse número registrado, por favor, atribua uma conta válida");
        }

        CreditCard savedCard = creditCardRepository.save(creditCard);

        return ResponseEntity.status(HttpStatus.CREATED).body("Cartão criado com sucesso");
    }
    @GetMapping("/all/{accountNumber}")
    public ResponseEntity<List<CreditCardReturnDTO>> getCreditCardsFromAccount(@PathVariable Integer accountNumber) {
        Optional<Account> optionalAccount = accountRepository.findByAccountNumber(accountNumber);

        if (optionalAccount.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Account account = optionalAccount.get();
        List<CreditCard> creditCards = account.getCreditCards();
        List<CreditCardReturnDTO> creditCardDTOs = new ArrayList<>();

        for (CreditCard creditCard : creditCards) {
            CreditCardReturnDTO creditCardDTO = creditCard.toCreditCardReturnDTO();
            creditCardDTOs.add(creditCardDTO);
        }

        return ResponseEntity.ok(creditCardDTOs);
    }
    @PutMapping("/updateLimit/{accountNumber}/{cardNumber}")
    public ResponseEntity updateLimit(@PathVariable Integer accountNumber, @PathVariable String cardNumber, @RequestBody CreditCardUpdateLimitDTO updateCardData){
        Optional<Account> optionalAccount = accountRepository.findByAccountNumber(accountNumber);

        if (optionalAccount.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Optional<CreditCard> optionalCard = creditCardRepository.findByCardNumber(cardNumber);
        if (optionalCard.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        CreditCard newCard = optionalCard.get();
        newCard.setCreditLimit(updateCardData.creditLimit());

        CreditCard savedCard = creditCardRepository.save(newCard);

        return ResponseEntity.status(HttpStatus.OK).body("Limite atualizado com sucesso!");
    }

        @DeleteMapping("/{accountNumber}/{cardNumber}")
    @Transactional
    public ResponseEntity deleteCardFromAccount(@PathVariable Integer accountNumber, @PathVariable String cardNumber){
        Optional<Account> optionalAccount = accountRepository.findByAccountNumber(accountNumber);

        if (optionalAccount.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Optional<CreditCard> optionalCard = creditCardRepository.findByCardNumber(cardNumber);
        if (optionalCard.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        creditCardRepository.deleteByCardNumber(cardNumber);
        return ResponseEntity.status(HttpStatus.OK).body("Cartão deletado com sucesso!");
    }
}
