package com.resellerapp.model.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
@Table(name = "offers")
public class Offer extends BaseEntity{

    private String description;

   @Positive
    private BigDecimal price;

    @ManyToOne
    private Condition condition;

    @ManyToOne
    private User creator;

    public User getCreator() {
        return creator;
    }

    public Offer setCreator(User creator) {
        this.creator = creator;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Offer setUser(User user) {
        this.user = user;
        return this;
    }

    @ManyToOne
    private User user;

    public String getDescription() {
        return description;
    }

    public Offer setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Offer setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Condition getCondition() {
        return condition;
    }

    public Offer setCondition(Condition condition) {
        this.condition = condition;
        return this;
    }
}
