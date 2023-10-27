package com.example.thymeleafprojecttest.model;


import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.sql.Types;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "employees")
public class Employee {


    @Id
    @JdbcTypeCode(Types.VARCHAR)
    private UUID id ;


    public Date getBirthdate() {
        return birthdate;
    }

    public Employee setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
        return this;
    }

    @Column(name = "birth_date", nullable = false)
    @DateTimeFormat(pattern = "mm/dd/yyyy")
    private Date birthdate;


    @Column(name = "education_level", nullable = false)
    private String educationLevel;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "job_title", nullable = false)
    private String jobTitle;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = false)
    private BigDecimal salary;

    @ManyToOne
    private Company company;

    public Employee() {
    }

    public UUID getId() {
        return id;
    }

    public Employee setId(UUID id) {
        this.id = id;
        return this;
    }



    public String getEducationLevel() {
        return educationLevel;
    }

    public Employee setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Employee setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public Employee setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Employee setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public Employee setSalary(BigDecimal salary) {
        this.salary = salary;
        return this;
    }

    public Company getCompany() {
        return company;
    }

    public Employee setCompany(Company company) {
        this.company = company;
        return this;
    }
}
