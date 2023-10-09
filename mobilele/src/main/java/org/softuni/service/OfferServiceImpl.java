package org.softuni.service;

import org.softuni.model.dto.CreateOfferDto;
import org.softuni.repository.OfferRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;

    public OfferServiceImpl(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    public UUID createOffer(CreateOfferDto createOfferDto) {

        return null;
    }
}
