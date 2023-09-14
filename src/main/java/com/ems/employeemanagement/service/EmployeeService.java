package com.ems.employeemanagement.service;

import com.ems.employeemanagement.model.Employee;
import com.ems.employeemanagement.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    public List<Employee> getEmployeesByDesignation(String designation){
        return employeeRepository.findByDesignation(designation);
    }

    public List<Employee> getEmployeesByname(String name) {
        return employeeRepository.findByName(name);
    }
}
