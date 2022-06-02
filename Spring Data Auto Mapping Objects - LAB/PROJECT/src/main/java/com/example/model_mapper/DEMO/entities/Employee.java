package com.example.model_mapper.DEMO.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Employee {

    private String firstName;
    private  String lastName;
    private BigDecimal salary;
    private LocalDate birthday;
    private Address address;
    private boolean onVacation;
    private Employee manager;
    private Set<Employee> subordinates;

    public Set<Employee> getSubordinates() {
        return subordinates;
    }

    public void setSubordinates(Set<Employee> subordinates) {
        this.subordinates = subordinates;
    }

    public Employee(String firstName, String lastName, BigDecimal salary, LocalDate birthday, Address address, boolean onVacation) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.birthday = birthday;
        this.address = address;
        this.onVacation = onVacation;
        this.subordinates = new HashSet<>();
    }

    public boolean isOnVacation() {
        return onVacation;
    }

    public void setOnVacation(boolean onVacation) {
        this.onVacation = onVacation;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public Set<Employee> getEmployees() {
        return subordinates;
    }

    public void setEmployees(Set<Employee> employees) {
        this.subordinates = employees;
    }



    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

  public void addEmployee (Employee employee){
        this.subordinates.add(employee);
  }


}
