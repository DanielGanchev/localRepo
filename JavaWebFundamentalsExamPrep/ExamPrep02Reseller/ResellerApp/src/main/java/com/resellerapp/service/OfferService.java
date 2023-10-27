package com.resellerapp.service;

import com.resellerapp.model.dto.OfferHomeViewModel;
import com.resellerapp.model.dto.OffersAddBindingModel;


public interface OfferService {
    void add(OffersAddBindingModel offersAddBindingModel, String username);

    void remove(Long id);

    void assign(Long id, String username);

    OfferHomeViewModel getHomeViewData(String username);
}
