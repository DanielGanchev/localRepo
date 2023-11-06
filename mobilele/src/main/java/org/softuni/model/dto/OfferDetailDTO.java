package org.softuni.model.dto;

import org.softuni.model.entities.enums.EngineEnum;
import org.softuni.model.entities.enums.TransmissionEnum;

import java.math.BigDecimal;

public record OfferDetailDTO(String uuid, String brand, String model, String imageUrl, int year, long mileage, BigDecimal price, EngineEnum engine,
                              TransmissionEnum transmission) {


    public String summary(){
        return year + " " + brand + ", " + model;
    }



}
