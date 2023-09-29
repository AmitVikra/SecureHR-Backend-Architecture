package com.ems.employeemanagement.service;

import com.ems.employeemanagement.model.Address;
import com.ems.employeemanagement.model.Employee;
import com.ems.employeemanagement.model.Vehicle;
import com.ems.employeemanagement.repository.AddressRepository;
import com.ems.employeemanagement.repository.VehicleRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private EntityManager em;

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

    public List<Employee> searchEmployees(String name, String designation, String city) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Address> addressRoot = criteriaQuery.from(Address.class);

        // Join with EmployeeDetails
        Join<Address, Employee> detailsJoin = addressRoot.join("employee", JoinType.INNER);

        // Specify the columns you want in the result
        criteriaQuery.select(detailsJoin);

        // List to hold predicates for different criteria
        List<Predicate> predicates = new ArrayList<>();

        // Add predicates for filtering by name, designation, and city
        if (name != null) {
            predicates.add(criteriaBuilder.like(detailsJoin.get("name"), "%" + name + "%"));
        }

        if (designation != null) {
            predicates.add(criteriaBuilder.like(detailsJoin.get("designation"), "%" + designation + "%"));
        }

        if (city != null) {
            predicates.add(criteriaBuilder.equal(addressRoot.get("city"), city));
        }

        // Combine predicates with AND
        criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));

        TypedQuery<Employee> typedQuery = em.createQuery(criteriaQuery);

        return typedQuery.getResultList();
    }
}
//test