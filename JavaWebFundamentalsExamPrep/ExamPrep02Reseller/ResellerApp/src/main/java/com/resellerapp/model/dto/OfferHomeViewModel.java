package com.resellerapp.model.dto;

import java.util.ArrayList;
import java.util.List;

public class OfferHomeViewModel {

    private List<OfferDto> availableOffers;

    private List<OfferDto> ownOffers;
    private List<OfferDto> boughtOffers;

    private int availableSize;

    public OfferHomeViewModel() {
        this(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    }


    public OfferHomeViewModel(List<OfferDto> availableOffers, List<OfferDto> ownOffers, List<OfferDto> boughtOffers) {
        this.availableOffers = availableOffers;
        this.ownOffers = ownOffers;
        this.boughtOffers = boughtOffers;
        this.availableSize = availableOffers.size();
    }

    public List<OfferDto> getAvailableOffers() {
        return availableOffers;
    }

    public OfferHomeViewModel setAvailableOffers(List<OfferDto> availableOffers) {
        this.availableOffers = availableOffers;
        return this;
    }

    public List<OfferDto> getOwnOffers() {
        return ownOffers;
    }

    public OfferHomeViewModel setOwnOffers(List<OfferDto> ownOffers) {
        this.ownOffers = ownOffers;
        return this;
    }

    public List<OfferDto> getBoughtOffers() {
        return boughtOffers;
    }

    public OfferHomeViewModel setBoughtOffers(List<OfferDto> boughtOffers) {
        this.boughtOffers = boughtOffers;
        return this;
    }

    public int getAvailableSize() {
        return availableSize;
    }

    public OfferHomeViewModel setAvailableSize(int availableSize) {
        this.availableSize = availableSize;
        return this;
    }
}
