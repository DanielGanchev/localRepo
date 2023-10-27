package com.example.thymeleafprojecttest.controller;


import com.example.thymeleafprojecttest.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AllEmployeesController {

    private final EmployeeService employeeService;

    public AllEmployeesController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees/all")
    public String allEmployees() {
        return "employee-all";
    }
}
