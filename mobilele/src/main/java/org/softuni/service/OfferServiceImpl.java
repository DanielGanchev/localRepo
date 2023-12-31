package org.softuni.service;

import jakarta.transaction.Transactional;
import org.softuni.model.dto.CreateOfferDto;

import org.softuni.model.dto.OfferDetailDTO;
import org.softuni.model.dto.OfferSummaryDTO;
import org.softuni.model.entities.ModelEntity;
import org.softuni.model.entities.OfferEntity;
import org.softuni.repository.ModelRepository;
import org.softuni.repository.OfferRepository;
import org.softuni.service.exception.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
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

    @Override
    public Page<OfferSummaryDTO> getAllOffers(Pageable pageable) {
        return offerRepository.findAll(pageable).map(OfferServiceImpl::mapAsSummary);

    }

    @Override
    public Optional<OfferDetailDTO> getOfferDetails(UUID uuid) {
        return offerRepository.findByUuid(uuid).map(OfferServiceImpl::mapAsDetail);
    }

    @Override
    @Transactional
    public void deleteOffer(UUID uuid) {
        offerRepository.deleteByUuid(uuid);
    }

    private static OfferSummaryDTO mapAsSummary(OfferEntity offerEntity) {
        return new OfferSummaryDTO(
                offerEntity.getUuid().toString(),
                offerEntity.getModel().getBrand().getName(),
                offerEntity.getModel().getName(),
                offerEntity.getImageUrl(),
                offerEntity.getYear(),
                offerEntity.getMileage(),
                offerEntity.getPrice(),
                offerEntity.getEngine(),
                offerEntity.getTransmission()
        );
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

    private static OfferDetailDTO mapAsDetail(OfferEntity offerEntity) {
        return new OfferDetailDTO(
                offerEntity.getUuid().toString(),
                offerEntity.getModel().getBrand().getName(),
                offerEntity.getModel().getName(),
                offerEntity.getImageUrl(),
                offerEntity.getYear(),
                offerEntity.getMileage(),
                offerEntity.getPrice(),
                offerEntity.getEngine(),
                offerEntity.getTransmission()
        );
    }
}