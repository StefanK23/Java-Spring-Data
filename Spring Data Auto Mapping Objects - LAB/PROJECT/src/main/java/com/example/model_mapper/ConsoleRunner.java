package com.example.model_mapper;

import com.example.model_mapper.entites.Employee;
import com.example.model_mapper.entites.dto.EmployeeSpringDTO;
import com.example.model_mapper.services.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final EmployeeService employeeService;

    @Autowired
    public ConsoleRunner(EmployeeService employeeService) {

        this.employeeService = employeeService;
    }


    @Override
    public void run(String... args) throws Exception {
       this.persist();

        List<Employee> list = this.employeeService.findEmployeesBornBefore(1990);


        ModelMapper mapper  = new ModelMapper();

        list.stream()
                .map(e -> mapper.map(e,EmployeeSpringDTO.class))
                .forEach(System.out::println);
    }

    private void persist() {
        Employee manager = new Employee("Manager", "last", BigDecimal.TEN, LocalDate.of(1700,6,1), null);

        Employee employeeFirst = new Employee("Stefan", "Kumanov", BigDecimal.ONE, LocalDate.of(1889,1,1), manager);
        Employee employeeSecond = new Employee("second", "last", BigDecimal.TEN, LocalDate.now(), manager);

        this.employeeService.safe(employeeFirst);
    }
}
