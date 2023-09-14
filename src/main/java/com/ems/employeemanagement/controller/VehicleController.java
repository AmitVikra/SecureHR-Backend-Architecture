package com.ems.employeemanagement.controller;

import com.ems.employeemanagement.exception.ResourceNotFoundException;
import com.ems.employeemanagement.model.Employee;
import com.ems.employeemanagement.model.Vehicle;
import com.ems.employeemanagement.repository.EmployeeRepository;
import com.ems.employeemanagement.repository.VehicleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VehicleController {
    private final VehicleRepository vehicleRepository;
    public VehicleController(VehicleRepository vehicleRepository){
        this.vehicleRepository = vehicleRepository;
    }
    @GetMapping("/vehicles")
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    @GetMapping("/vehicles/{number}")
    public ResponseEntity<Vehicle> getVehicleByNumber(@PathVariable(value = "number") Long vehicleNumber)
            throws ResourceNotFoundException {
        Vehicle vehicle = vehicleRepository.findById(vehicleNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found for this number :: " + vehicleNumber));
        return ResponseEntity.ok().body(vehicle);
    }

}
