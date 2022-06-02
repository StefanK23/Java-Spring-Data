package com.example.model_mapper.repositories;

import com.example.model_mapper.entites.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeRepository  extends JpaRepository<Employee, Integer> {

    List<Employee> findByBirthdayBeforeOrderBySalaryDesc(LocalDate beforeYear);
}
