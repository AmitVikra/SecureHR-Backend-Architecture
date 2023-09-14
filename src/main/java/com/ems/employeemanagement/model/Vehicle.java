package com.ems.employeemanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "vehicle_details")
public class Vehicle {
    @Id
    @Column(unique = true)
    private int number;
    private String model;
    private String type;
    private int horsepower;

    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
