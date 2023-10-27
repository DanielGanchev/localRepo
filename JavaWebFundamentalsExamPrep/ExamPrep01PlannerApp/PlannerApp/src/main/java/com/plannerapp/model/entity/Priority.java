package com.plannerapp.model.entity;

import com.plannerapp.model.enums.PriorityName;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "priorities")
public class Priority extends BaseEntity{



    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private PriorityName name;

    @NotNull
    private String description;

    @OneToMany(mappedBy = "priority")
    private Set<Task> tasks;

    private void setDescription(PriorityName name){
        String description = "";

        switch (name) {
            case URGENT:
                description = "An urgent problem that blocks the system use until the issue is resolved.";
                break;
            case IMPORTANT:
                description = "A core functionality that your product is explicitly supposed to perform is compromised.";
                break;
            case LOW:
                description = "Should be fixed if time permits but can be postponed.";
                break;
        }

        this.description = description;
    }

    public PriorityName getName() {
        return name;
    }

    public Priority setName(PriorityName name) {
        this.name = name;
        setDescription(name);
        return this;
    }

    public String getDescription() {
        return description;
    }



    public Set<Task> getTasks() {
        return tasks;
    }

    public Priority setTasks(Set<Task> tasks) {
        this.tasks = tasks;
        return this;
    }
}
