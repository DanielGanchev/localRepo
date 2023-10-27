package com.example.thymeleafprojecttest.controller;

import com.example.thymeleafprojecttest.service.CompanyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AllCompaniesController {

    private final CompanyService companyService;

    public AllCompaniesController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/companies/all")
    public String allCompanies() {
        return "company-all";
    }
}
