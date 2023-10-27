package org.softuni.service;

import org.softuni.model.dto.CreateOfferDto;


import java.util.UUID;

public interface OfferService {

    UUID createOffer(CreateOfferDto createOfferDto);


}
