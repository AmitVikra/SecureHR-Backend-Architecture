package com.ems.employeemanagement.controller;

import com.ems.employeemanagement.model.Address;
import com.ems.employeemanagement.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AddressController {
    @Autowired
    private AddressRepository addressRepository;
    @GetMapping("/address")
    public List<Address> getAllAddress(){
        return addressRepository.findAll();
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
