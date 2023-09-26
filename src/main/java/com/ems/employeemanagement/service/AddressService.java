package com.ems.employeemanagement.service;


import com.ems.employeemanagement.model.Address;
import com.ems.employeemanagement.model.Employee;
import com.ems.employeemanagement.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;

}
