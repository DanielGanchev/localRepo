package com.resellerapp.model.dto;

import com.resellerapp.model.enums.ConditionName;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;


public class OffersAddBindingModel {

    @Size(min = 2, max = 50, message = "Description length must be between 2 and 50 characters!")
    private String description;

    @Positive(message = "Price must be positive number!")
    private double price;

    @NotNull(message = "You must select a condition!")
    private ConditionName condition;

    public String getDescription() {
        return description;
    }



    public double getPrice() {
        return price;
    }



    public ConditionName getCondition() {
        return condition;
    }

    public OffersAddBindingModel setCondition(ConditionName condition) {
        this.condition = condition;
        return this;
    }
}
