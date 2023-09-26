package com.ems.employeemanagement.repository;

import com.ems.employeemanagement.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findAddressByCity(String city);
    List<Address> findAddressByCountry(String country);
    List<Address> findAddressByZipCode(int zipCode);
    List<Address> findAddressByStreetName(String streetName);

}
