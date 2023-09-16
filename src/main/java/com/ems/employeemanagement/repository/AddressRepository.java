package com.ems.employeemanagement.repository;

import com.ems.employeemanagement.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findAddressByCityName(String cityName);

    List<Address> findAddressByCountry(String countryName);

}
