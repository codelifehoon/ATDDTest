package com.example.employee;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GreetingService {


    EmployeeRepository repository;

    public GreetingService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public String greet(String lastName) {

        Optional<Employee> employee = repository.findByLastName(lastName);

        return employee.map(e -> String.format("Hello %s %s!", e.getFirstName(), e.getLastName()))
                   .orElse("Who is this " + lastName + " you're talking about?");

    }
}