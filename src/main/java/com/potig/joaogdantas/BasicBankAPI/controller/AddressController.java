package com.potig.joaogdantas.BasicBankAPI.controller;

import com.potig.joaogdantas.BasicBankAPI.domain.address.dto.AddressCreateDTO;
import com.potig.joaogdantas.BasicBankAPI.domain.address.dto.AddressUpdateDTO;
import com.potig.joaogdantas.BasicBankAPI.domain.address.model.Address;
import com.potig.joaogdantas.BasicBankAPI.domain.address.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private AddressRepository repository;
    private Address address;

    @PostMapping
    public ResponseEntity createAddress(@RequestBody AddressCreateDTO addressData){
        Address address = new Address();

        address.setLine1(addressData.line_1());
        address.setLine2(addressData.line_2());
        address.setPostalCode(addressData.postalCode());
        address.setCity(addressData.city());
        address.setCountry(addressData.country());

        Address savedAddress = repository.save(address);

        return ResponseEntity.status(HttpStatus.OK).body("Endereço criado com sucesso!");
    }
    @GetMapping("/all")
    public ResponseEntity<List<AddressCreateDTO>> findAllAddresses(){
        var addresses = repository.findAll();
        List<AddressCreateDTO> result = new ArrayList<>();

        addresses.forEach(a -> result.add(new AddressCreateDTO(a.getLine1(), a.getLine2(), a.getPostalCode(), a.getCity(), a.getCountry())));

        return ResponseEntity.ok(result);
    }
    @PutMapping("/update/{postalCode}")
    public ResponseEntity updateAddress(@PathVariable String postalCode, @RequestBody AddressUpdateDTO addressDTO) {
        Optional<Address> optionalAddress = repository.findByPostalCode(postalCode);

        if (optionalAddress.isPresent()) {
            Address address = optionalAddress.get();
            address.setLine1(addressDTO.line_1());
            address.setLine2(addressDTO.line_2());
            address.setCity(addressDTO.city());
            address.setCountry(addressDTO.country());

            Address savedAddress = repository.save(address);

            return ResponseEntity.status(HttpStatus.OK).body("Endereço atualizado com sucesso");
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("{postalCode}")
    @Transactional
    public ResponseEntity deleteAddress(@PathVariable String postalCode){
        Optional<Address> optionalAddress = repository.findByPostalCode(postalCode);

        if(optionalAddress.isPresent()){
            repository.deleteByPostalCode(postalCode);
            return ResponseEntity.status(HttpStatus.OK).body("Endereço deletado com sucesso!");
        }
        return ResponseEntity.notFound().build();
    }
}

