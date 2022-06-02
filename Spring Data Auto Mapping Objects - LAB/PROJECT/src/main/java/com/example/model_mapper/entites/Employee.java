package com.example.model_mapper.entites;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name = "employees")
public class Employee {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastname;

    private BigDecimal salary;
    private LocalDate birthday;

    @ManyToOne(cascade = CascadeType.ALL)
    private Employee Manager;

    public Employee() {

    }

    public Employee(String firstName, String lastname, BigDecimal salary, LocalDate birthday, Employee manager) {
        this.firstName = firstName;
        this.lastname = lastname;
        this.salary = salary;
        this.birthday = birthday;
        Manager = manager;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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

    public Employee getManager() {
        return Manager;
    }

    public void setManager(Employee manager) {
        Manager = manager;
    }
}
