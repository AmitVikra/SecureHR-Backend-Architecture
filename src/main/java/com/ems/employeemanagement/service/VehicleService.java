package com.ems.employeemanagement.service;

import com.ems.employeemanagement.model.Employee;
import com.ems.employeemanagement.model.Vehicle;
import com.ems.employeemanagement.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;
    public List<Vehicle> getVehicleByModel(String VehicleModel) {
        return vehicleRepository.findVehicleByModel(VehicleModel);
    }
    public List<Vehicle> getVehicleByType(String VehicleType){
        return vehicleRepository.findVehicleByType((VehicleType));
    }


}
