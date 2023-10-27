package com.resellerapp.model.dto;

import com.resellerapp.model.entity.Offer;
import com.resellerapp.model.enums.ConditionName;

import java.math.BigDecimal;

public class OfferDto {

    private Long id;
    private String description;
    private double price;
    private ConditionName condition;

    private String creator;

    public String getCreator() {
        return creator;
    }

    public Long getId() {
        return id;
    }

    public OfferDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OfferDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public double getPrice() {
        return price;
    }



    public ConditionName getCondition() {
        return condition;
    }

    public OfferDto setCondition(ConditionName condition) {
        this.condition = condition;
        return this;
    }



    public OfferDto setCreator(String creator) {
        this.creator = creator;
        return this;
    }

    public static OfferDto createFromOffer(Offer offer) {
        OfferDto offerDto = new OfferDto();

        offerDto.setId(offer.getId());
        offerDto.setDescription(offer.getDescription());
        offerDto.setPrice(offer.getPrice());
        offerDto.setCondition(offer.getCondition().getName());
        offerDto.setCreator(offer.getCreator().getUsername());

        return offerDto;
    }

    private void setPrice(BigDecimal price) {

        this.price = price.doubleValue();
    }
}
