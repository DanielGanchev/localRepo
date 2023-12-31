package org.softuni.service;

import org.softuni.model.dto.CreateOfferDto;
import org.softuni.model.dto.OfferDetailDTO;
import org.softuni.model.dto.OfferSummaryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.Optional;
import java.util.UUID;

public interface OfferService {

    UUID createOffer(CreateOfferDto createOfferDto);

    Page<OfferSummaryDTO> getAllOffers(Pageable pageable);

    Optional<OfferDetailDTO> getOfferDetails(UUID uuid);

    void deleteOffer(UUID uuid);


}
