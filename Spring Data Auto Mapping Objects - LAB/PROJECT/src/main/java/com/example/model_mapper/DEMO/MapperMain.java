package com.example.model_mapper.DEMO;

import com.example.model_mapper.DEMO.DTO.EmployeeDTO;
import com.example.model_mapper.DEMO.DTO.ManagerDTO;
import com.example.model_mapper.DEMO.entities.Address;
import com.example.model_mapper.DEMO.entities.Employee;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MapperMain {
    public static void main(String[] args) {

        ModelMapper mapper = new ModelMapper();
        Address address = new Address("Mariya Luiza", 36, "Plovdiv", "Bulgaria");

        Employee manager = new Employee("Stefan", "Kumanov", BigDecimal.TEN, LocalDate.now(), address, true);

        Employee employeeFirst = new Employee("Atanas", "Kumanov", BigDecimal.valueOf(1333), LocalDate.now(), address, true);
        Employee employeeSecond = new Employee("Dimitar", "Hubinov", BigDecimal.valueOf(1300), LocalDate.now(), address, true);

        manager.addEmployee(employeeFirst);
        manager.addEmployee(employeeSecond);

        ManagerDTO dto = mapper.map(manager, ManagerDTO.class);
        System.out.println(dto);
    }
}
