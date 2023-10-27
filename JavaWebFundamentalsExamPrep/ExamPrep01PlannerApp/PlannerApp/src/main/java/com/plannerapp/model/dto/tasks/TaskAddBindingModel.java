package com.plannerapp.model.dto.tasks;


import com.plannerapp.model.enums.PriorityName;
import com.plannerapp.vallidation.StringDateInTheFuture;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TaskAddBindingModel {

    @Size(min = 2, max = 50, message = "Description length must be between 2 and 50 characters!")
    private String description;

    @StringDateInTheFuture(message = "Due Date must be in the future!")
    private String dueDate;

    @NotNull(message = "You must select a priority!")
    private PriorityName priority;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String  getDueDate() {
        return dueDate;
    }

    public void setDueDate(String  dueDate) {
        this.dueDate = dueDate;
    }

    public PriorityName getPriority() {
        return priority;
    }

    public void setPriority(PriorityName priority) {
        this.priority = priority;
    }
}
