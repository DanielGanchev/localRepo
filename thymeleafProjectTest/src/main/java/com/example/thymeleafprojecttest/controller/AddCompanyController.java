package com.example.thymeleafprojecttest.controller;

import com.example.thymeleafprojecttest.service.CompanyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AddCompanyController {

    private final CompanyService companyService;

    public AddCompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/companies/add")
    public String addCompany() {
        return "company-add";
    }


}
