package com.ems.employeemanagement.service;

import com.ems.employeemanagement.model.Address;
import com.ems.employeemanagement.model.Employee;
import com.ems.employeemanagement.model.Vehicle;
import com.ems.employeemanagement.repository.AddressRepository;
import com.ems.employeemanagement.repository.EmployeeRepository;
import com.ems.employeemanagement.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private AddressRepository addressRepository;

    public List<Employee> getEmployeesByDesignation(String designation){
        return employeeRepository.findByDesignation(designation);
    }
    public List<Employee> getEmployeesByname(String name) {
        return employeeRepository.findByName(name);
    }
    public List<Employee> getEmployeesByAgeRange(int minAge, int maxAge) {
        return employeeRepository.findByAgeBetween(minAge,maxAge);
    }
    public List<Employee> findEmployeesByVehicleType(String vehicleType) {
        List<Vehicle> vehicles = vehicleRepository.findVehicleByType(vehicleType);
        List<Employee> employees = vehicles.stream()
                .map(Vehicle::getEmployee)
                .collect(Collectors.toList());
        return employees;
    }
    public List<Employee> getEmployeesByCity(String cityName){
        List<Address> addresses = addressRepository.findAddressByCityName(cityName);
        List<Employee> employees = addresses.stream().map(Address::getEmployee).collect(Collectors.toList());
        return employees;
    }

}
