package com.ems.employeemanagement.repository;

import com.ems.employeemanagement.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{


    List<Employee> findByDesignation(String designation);
    List<Employee> findByName(String name);
    List<Employee> findEmployeeByAgeGreaterThan(Integer minAge);
    List<Employee> findEmployeeByAgeLessThan(Integer maxAge);
}


