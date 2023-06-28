package com.potig.joaogdantas.BasicBankAPI.controller;

import com.potig.joaogdantas.BasicBankAPI.domain.account.model.Account;
import com.potig.joaogdantas.BasicBankAPI.domain.account.repository.AccountRepository;
import com.potig.joaogdantas.BasicBankAPI.domain.address.model.Address;
import com.potig.joaogdantas.BasicBankAPI.domain.address.repository.AddressRepository;
import com.potig.joaogdantas.BasicBankAPI.domain.customer.dto.CustomerCreateDTO;
import com.potig.joaogdantas.BasicBankAPI.domain.customer.dto.CustomerUpdateDTO;
import com.potig.joaogdantas.BasicBankAPI.domain.customer.model.Customer;
import com.potig.joaogdantas.BasicBankAPI.domain.customer.repository.CustomerRepository;
import com.potig.joaogdantas.BasicBankAPI.domain.customer.dto.CustomerReturnDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerRepository repository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AddressRepository addressRepository;
    private Customer customer;
    private Address address;
    private Account account;
    @PostMapping
    public ResponseEntity createCustomer(@RequestBody CustomerCreateDTO customerData) {
        Customer customer = new Customer();

        customer.setName(customerData.name());

        Optional<Account> optionalAccount = accountRepository.findByAccountNumber(customerData.accountNumber());
        if (optionalAccount.isPresent()) {
            customer.setAccount(optionalAccount.get());
        } else {
            return ResponseEntity.badRequest().body("Não existe uma conta com esse número registrado, por favor, atribua uma conta válida");
        }

        Optional<Address> optionalAddress = addressRepository.findByPostalCode(customerData.postalCode());
        if (optionalAddress.isPresent()) {
            customer.setAddress(optionalAddress.get());
        } else {
            return ResponseEntity.badRequest().body("Não existe um endereço com esse código postal registrado, por favor, atribua um endereço válido");
        }

        customer.setAge(customerData.age());
        customer.setDocumentNumber(customerData.documentNumber());

        Customer savedCustomer = repository.save(customer);

        return ResponseEntity.status(HttpStatus.CREATED).body("Cliente cadastrado com sucesso!");
    }


    @GetMapping("/{documentNumber}")
    public ResponseEntity getByDocument(@PathVariable String documentNumber) {
        Optional<Customer> optionalCustomer = repository.findByDocument(documentNumber);
        return optionalCustomer.map(customer -> ResponseEntity.ok(new CustomerReturnDTO(customer)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    public ResponseEntity<List<CustomerReturnDTO>> findAllCustomers() {
        var customers = repository.findAll();
        List<CustomerReturnDTO> result = new ArrayList<>();
        customers.forEach(c -> result.add(new CustomerReturnDTO(c.getName(), c.getDocumentNumber(), c.getAge())));
        return ResponseEntity.ok(result);
    }

    @PutMapping("/update/{documentNumber}")
    public ResponseEntity updateCustomer(@PathVariable String documentNumber, @RequestBody CustomerUpdateDTO updatedCustomer) {
        Optional<Customer> optionalCustomer = repository.findByDocument(documentNumber);

        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();

            customer.setName(updatedCustomer.name());
            customer.setAge(updatedCustomer.age());

            Customer savedCustomer = repository.save(customer);

            return ResponseEntity.status(HttpStatus.OK).body("Dados do cliente atualizados com sucesso!");
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{documentNumber}")
    @Transactional
    public ResponseEntity deleteCustomerByDocument(@PathVariable String documentNumber) {
        Optional<Customer> optionalCustomer = repository.findByDocument(documentNumber);

        if (optionalCustomer.isPresent()){
            repository.deleteByDocumentNumber(documentNumber);
            return ResponseEntity.status(HttpStatus.OK).body("Dados do cliente deletados com sucesso!");
        }
        return ResponseEntity.notFound().build();
    }
}