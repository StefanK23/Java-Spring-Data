package com.example.model_mapper.services;

import com.example.model_mapper.entites.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService  {

    Optional<Employee> findOneById(int id);

    void safe(Employee employee);

     List<Employee> findEmployeesBornBefore(int year);

}
