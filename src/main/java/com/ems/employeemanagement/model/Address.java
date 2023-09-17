package com.ems.employeemanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.print.attribute.standard.MediaSize;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Address {

    private String houseNumber;
    private String streetName;
    private String cityName;
    private String country;
    private int zipCode;

    @Id
    @Column(name = "Phone_number", unique = true, nullable = false)
    private Long phoneNumber;

    @OneToOne
    @JoinColumn(name = "employee_id",nullable = false)
    private Employee employee;

    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;
}
