package com.ems.employeemanagement.controller;

import com.ems.employeemanagement.model.Address;
import com.ems.employeemanagement.repository.AddressRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees/address")
public class AddressController {
    @Autowired
    private AddressRepository addressRepository;
    @PostMapping("address")
    public Address createAddress(@Valid @RequestBody Address address){
        return addressRepository.save(address);
    }
    @GetMapping("")
    public List<Address> getAddress(
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) Integer zipCode,
            @RequestParam(required = false) String streetName) {

        if (city != null) {
            // Call the method to find employees by name
            return addressRepository.findAddressByCity(city);
        } else if (country != null) {
            // Call the method to find employees by designation
            return addressRepository.findAddressByCountry(country);
        } else if (zipCode != null) {
            // Call the method to find employees by age range
            return addressRepository.findAddressByZipCode(zipCode);
        } else if (streetName != null) {
            // Call the method to find employees by age range
            return addressRepository.findAddressByStreetName(streetName);
        } else {
            // Handle the case where none of the parameters are provided
            return addressRepository.findAll(); // or return all employees if needed
        }
    }
}
