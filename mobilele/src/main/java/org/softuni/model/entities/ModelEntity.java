package org.softuni.model.entities;

import jakarta.persistence.*;
import org.softuni.model.entities.enums.ModelCategoryEnum;

@Entity
@Table(name = "models")
public class ModelEntity extends BaseEntity{

    @Column(nullable = false,unique = true)
    private String name;


    @Enumerated(EnumType.STRING)
    private ModelCategoryEnum category;

    @ManyToOne
    private BrandEntity brand;

    public ModelEntity() {
    }

    public String getName() {
        return name;
    }

    public ModelEntity setName(String name) {
        this.name = name;
        return this;
    }

    public ModelCategoryEnum getCategory() {
        return category;
    }

    public ModelEntity setCategory(ModelCategoryEnum category) {
        this.category = category;
        return this;
    }

    public BrandEntity getBrand() {
        return brand;
    }

    public ModelEntity setBrand(BrandEntity brand) {
        this.brand = brand;
        return this;
    }
}
