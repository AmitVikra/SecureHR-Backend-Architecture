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
    private int vehicle_number;
    private int horsepower;

    @OneToOne
    private Employee employee;
}
