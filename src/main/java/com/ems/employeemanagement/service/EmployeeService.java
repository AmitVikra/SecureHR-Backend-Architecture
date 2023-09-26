package com.ems.employeemanagement.service;

import com.ems.employeemanagement.model.Address;
import com.ems.employeemanagement.model.Employee;
import com.ems.employeemanagement.model.Vehicle;
import com.ems.employeemanagement.repository.AddressRepository;
import com.ems.employeemanagement.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private AddressRepository addressRepository;

    public List<Employee> findEmployeesByVehicleType(String vehicleType) {
        List<Vehicle> vehicles = vehicleRepository.findVehicleByType(vehicleType);
        List<Employee> employees = vehicles.stream()
                .map(Vehicle::getEmployee)
                .collect(Collectors.toList());
        return employees;
    }
    public List<Employee> findEmployeesByCity(String city){
        List<Address> addresses = addressRepository.findAddressByCity(city);
        List<Employee> employees = addresses.stream().map(Address::getEmployee).collect(Collectors.toList());
        return employees;
    }
}
