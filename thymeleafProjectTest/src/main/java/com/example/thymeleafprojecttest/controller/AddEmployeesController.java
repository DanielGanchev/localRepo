package com.example.thymeleafprojecttest.controller;

import com.example.thymeleafprojecttest.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AddEmployeesController {

    private final EmployeeService employeeService;

    public AddEmployeesController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees/add")
    public String add(){
        return "employee-add";
    }
}
