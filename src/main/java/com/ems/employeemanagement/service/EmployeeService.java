package com.ems.employeemanagement.service;

import com.ems.employeemanagement.model.Employee;
import com.ems.employeemanagement.model.Vehicle;
import com.ems.employeemanagement.repository.EmployeeRepository;
import com.ems.employeemanagement.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final VehicleRepository vehicleRepository;
    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, VehicleRepository vehicleRepository) {
        this.employeeRepository = employeeRepository;
        this.vehicleRepository = vehicleRepository;
    }
    public List<Employee> getEmployeesByDesignation(String designation){
        return employeeRepository.findByDesignation(designation);
    }

    public List<Employee> getEmployeesByname(String name) {
        return employeeRepository.findByName(name);
    }

    public List<Employee> getEmployeesByAgeRange(int minAge, int maxAge) {
        return employeeRepository.findByAgeBetween(minAge,maxAge);
    }

//    @Autowired

    public List<Employee> findEmployeesByVehicleType(String vehicleType) {
        List<Vehicle> vehicles = vehicleRepository.findVehicleByType(vehicleType);
        List<Employee> employees = vehicles.stream()
                .map(Vehicle::getEmployee)
                .collect(Collectors.toList());
        return employees;
        }
}
