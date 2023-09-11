package com.ems.employeemanagement.model;

import javax.persistence.*;

@Entity
@Table(name = "employee_details")
public class Employee {

    private long id;
    private String name;
    private int age;
    private String designation;
    private int salary;

    public Employee() {

    }

    public Employee(String name, int age, String designation, int salary) {
        this.name = name;
        this.age = age;
        this.designation = designation;
        this.salary = salary;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @Column(name = "age")
    public int getAge() { return age; }

    public void setAge(int age) {
        this.age = age;
    }

    @Column(name = "designation")
    public String getDesignation() {
        return designation;
    }
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    @Column(name = "salary")
    public int getSalary() {
        return salary;
    }
    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + ", age=" + age + ", designation=" + designation+", Salary="+ salary+"]";
    }

}