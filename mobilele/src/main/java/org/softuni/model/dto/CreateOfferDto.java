package org.softuni.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.softuni.model.entities.enums.EngineEnum;
import org.softuni.model.entities.enums.TransmissionEnum;
import validation.YearNotInTheFuture;


public record CreateOfferDto(@NotEmpty @Size(min = 5, max = 512) String description,
                             @Positive @NotNull Long modelId, @NotNull EngineEnum engine,
                             @NotNull TransmissionEnum transmission, @NotEmpty String imageUrl,
                             @Positive @NotNull Integer mileage,
                             @Positive @NotNull Integer price,
                             @YearNotInTheFuture(message = "The year should not be in the future!")
                             @NotNull(message = "Year must be provided!")
                             @Min(1930)
                             Integer year) {

    public static CreateOfferDto empty() {
        return new CreateOfferDto(null, null, null, null, null, null, null, null);
    }
}