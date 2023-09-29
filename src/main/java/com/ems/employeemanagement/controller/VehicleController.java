package com.ems.employeemanagement.controller;

import com.ems.employeemanagement.model.Employee;
import com.ems.employeemanagement.model.Vehicle;
import com.ems.employeemanagement.repository.EmployeeRepository;
import com.ems.employeemanagement.repository.VehicleRepository;
import com.ems.employeemanagement.exception.ResourceNotFoundException;
import com.ems.employeemanagement.service.VehicleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/employees/vehicle")
public class VehicleController {
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/vehicle")
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    @PostMapping("/register")
    public Vehicle createVehicle(@Valid @RequestBody Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    @GetMapping("/{number}")
    public ResponseEntity<Vehicle> getVehicleByNumber(@PathVariable(value = "number") Long vehicleNumber)
            throws ResourceNotFoundException {
        Vehicle vehicle = vehicleRepository.findById(vehicleNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found for this number :: " + vehicleNumber));
        return ResponseEntity.ok().body(vehicle);
    }
    @GetMapping("/{employeeId}/vehicle")
    public ResponseEntity<Vehicle> getEmployeeVehicle(@PathVariable Long employeeId) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            Optional<Vehicle> optionalVehicle = vehicleRepository.findByEmployee(employee);
            if (optionalVehicle.isPresent()) {
                Vehicle vehicle = optionalVehicle.get();
                return ResponseEntity.ok(vehicle);
            }
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{number}")
    public Map<String, Boolean> deleteVehicle(@PathVariable(value = "number") Long number)
            throws ResourceNotFoundException {
        Vehicle vehicle = vehicleRepository.findById(number)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found for this id :: " + number));

        vehicleRepository.delete(vehicle);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
    @GetMapping("")
    public ResponseEntity<List<Vehicle>> searchVehicle(


            @RequestParam(name = "id", required = false) Long id,
            @RequestParam(name = "type", required = false) String type,
            @RequestParam(name = "model", required = false) String model,
            @RequestParam(name = "number", required = false) String number ){

        List<Vehicle> employees = vehicleService.searchVehicles(id, type, model, number);
        return ResponseEntity.ok(employees);
    }
}