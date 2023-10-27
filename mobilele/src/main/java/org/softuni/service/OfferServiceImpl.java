package org.softuni.service;

import org.softuni.model.dto.CreateOfferDto;

import org.softuni.model.entities.ModelEntity;
import org.softuni.model.entities.OfferEntity;
import org.softuni.repository.ModelRepository;
import org.softuni.repository.OfferRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;


@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final ModelRepository modelRepository;

    public OfferServiceImpl(OfferRepository offerRepository, ModelRepository modelRepository) {
        this.offerRepository = offerRepository;
        this.modelRepository = modelRepository;
    }

    @Override
    public UUID createOffer(CreateOfferDto createOfferDto) {

        OfferEntity offerEntity = map(createOfferDto);

        ModelEntity modelEntity = modelRepository.findById(createOfferDto.modelId())
                .orElseThrow(() -> new IllegalArgumentException("Model not found"));

        offerEntity.setModel(modelEntity);

        offerRepository.save(offerEntity);

        return offerEntity.getUuid();
    }

    private OfferEntity map(CreateOfferDto createOfferDto) {
        return new OfferEntity().setUuid(UUID.randomUUID())
                .setDescription(createOfferDto.description())
                .setEngine(createOfferDto.engine())
                .setTransmission(createOfferDto.transmission())
                .setImageUrl(createOfferDto.imageUrl())
                .setMileage(createOfferDto.mileage())
                .setPrice(BigDecimal.valueOf(createOfferDto.price()))
                .setYear(createOfferDto.year());


    }
}