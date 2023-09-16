package com.ems.employeemanagement.controller;

import com.ems.employeemanagement.model.Employee;
import com.ems.employeemanagement.security.exception.ResourceNotFoundException;
import com.ems.employeemanagement.model.Vehicle;
import com.ems.employeemanagement.repository.VehicleRepository;
import com.ems.employeemanagement.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VehicleController {
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private VehicleService vehicleService;

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

    @GetMapping("/vehicles/findbyModel")
    //vehicles/findbymodel?model=model_name
    public List<Vehicle> getVehicleByModel(@RequestParam String model){
        return vehicleService.getVehicleByModel(model);
    }

    @GetMapping("/vehicles/findbyType")
    //vehicles/findbyType?type = Vehicle_type
    public List<Vehicle> getVehicleByType(@RequestParam String type){
        return vehicleService.getVehicleByType(type);
    }


}
