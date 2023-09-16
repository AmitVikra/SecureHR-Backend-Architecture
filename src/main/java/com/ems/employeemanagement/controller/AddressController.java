package com.ems.employeemanagement.controller;

import com.ems.employeemanagement.model.Address;
import com.ems.employeemanagement.repository.AddressRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AddressController {
    @Autowired
    private AddressRepository addressRepository;
    @GetMapping("/public/address")
    public List<Address> getAllAddress(){
        return addressRepository.findAll();
    }
    @PostMapping("address")
    public Address createAddress(@Valid @RequestBody Address address){
        return addressRepository.save(address);
    }
    @GetMapping("address/findByCity")
    public List<Address> getAddressByCity(@RequestParam String city){
        return addressRepository.findAddressByCityName(city);
    }
    @GetMapping("address/findByCountry")
    public List<Address> getAddressByCountry(@RequestParam String country){
        return addressRepository.findAddressByCountry(country);
    }

}
