package com.ems.employeemanagement.service;

import com.ems.employeemanagement.model.Address;
import com.ems.employeemanagement.model.Employee;
import com.ems.employeemanagement.model.Vehicle;
import com.ems.employeemanagement.repository.VehicleRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private EntityManager em;

    public List<Vehicle> searchVehicles(Long id, String type, String model, String number) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Vehicle> criteriaQuery = criteriaBuilder.createQuery(Vehicle.class);
        Root<Vehicle> vehicleRoot = criteriaQuery.from(Vehicle.class);

        // Join with EmployeeDetails
        Join<Vehicle, Employee> detailsJoin = vehicleRoot.join("employee", JoinType.INNER);

        // List to hold predicates for different criteria
        List<Predicate> predicates = new ArrayList<>();

        // Add predicates for filtering by employee ID, type, and model
        if (id != null) {
            predicates.add(criteriaBuilder.equal(detailsJoin.get("id"), id));
        }

        if (type != null) {
            predicates.add(criteriaBuilder.equal(vehicleRoot.get("type"), type));
        }

        if (model != null) {
            predicates.add(criteriaBuilder.equal(vehicleRoot.get("model"), model));
        }

        if (number != null) {
            predicates.add(criteriaBuilder.equal(vehicleRoot.get("number"), number));
        }

        // Combine predicates with AND
        criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));

        TypedQuery<Vehicle> typedQuery = em.createQuery(criteriaQuery);

        return typedQuery.getResultList();
    }

}
