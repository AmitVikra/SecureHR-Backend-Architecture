package com.ems.employeemanagement.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ems.employeemanagement.model.Employee;

import java.util.List;
import java.util.Optional;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{


    List<Employee> findByDesignation(String designation);
    List<Employee> findByName(String name);

    List<Employee> findEmployeeByAgeGreaterThan(Integer minAge);

    List<Employee> findEmployeeByAgeLessThan(Integer maxAge);
}


