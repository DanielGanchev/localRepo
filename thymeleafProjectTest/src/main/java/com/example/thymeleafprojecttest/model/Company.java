package com.example.thymeleafprojecttest.model;


import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.util.UUID;


@Entity
@Table(name = "companies")
public class Company {

    @Id
    @JdbcTypeCode(Types.VARCHAR)
    private UUID id;


    @Column(nullable = false)
    private int budget;

    @Column(columnDefinition = "TEXT")
    private String description;


    @Column(unique = true,nullable = false)
    private String name;


    @Column(nullable = false)
    private String town;

    public Company() {
    }

    public UUID getId() {
        return id;
    }

    public Company setId(UUID id) {
        this.id = id;
        return this;
    }

    public int getBudget() {
        return budget;
    }

    public Company setBudget(int budget) {
        this.budget = budget;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Company setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getName() {
        return name;
    }

    public Company setName(String name) {
        this.name = name;
        return this;
    }

    public String getTown() {
        return town;
    }

    public Company setTown(String town) {
        this.town = town;
        return this;
    }
}
