package com.plannerapp.model.entity;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "tasks")
public class Task extends BaseEntity{

    @Length(min = 2, max = 50)
    @Column(nullable = false)
    private String description;

    @Future
    @Column(nullable = false)

    private LocalDate dueDate;


    @ManyToOne
    @NotNull
    private Priority priority;


    @ManyToOne
    private User assignee;

    public String getDescription() {
        return description;
    }

    public Task setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public Task setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public Priority getPriority() {
        return priority;
    }

    public Task setPriority(Priority priority) {
        this.priority = priority;
        return this;
    }

    public User getAssignee() {
        return assignee;
    }

    public Task setAssignee(User assignee) {
        this.assignee = assignee;
        return this;
    }
}
