package com.resellerapp.model.entity;

import com.resellerapp.model.enums.ConditionName;
import org.springframework.scheduling.config.Task;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "conditions")
public class Condition extends BaseEntity{


    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private ConditionName name;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "condition")
    private Set<Offer> offers;

    public ConditionName getName() {
        return name;
    }

 public void setName(ConditionName name) {
        this.name = name;
        setDescription(name);
    }

    public String getDescription() {
        return description;
    }

 private void setDescription(ConditionName name) {
        String description = "";

        switch (name) {
            case EXCELLENT -> description = "In perfect condition";
            case GOOD -> description = "Some signs of wear and tear or minor defects";
            case ACCEPTABLE -> description = "The item is fairly worn but continues to function properly";
        }

        this.description = description;
    }

    public Set<Offer> getOffers() {
        return offers;
    }

    public Condition setOffers(Set<Offer> offers) {
        this.offers = offers;
        return this;
    }
}
