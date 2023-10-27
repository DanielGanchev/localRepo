package com.resellerapp.service.impl;

import com.resellerapp.model.dto.OfferDto;
import com.resellerapp.model.dto.OfferHomeViewModel;
import com.resellerapp.model.dto.OffersAddBindingModel;
import com.resellerapp.model.entity.Condition;
import com.resellerapp.model.entity.Offer;
import com.resellerapp.model.entity.User;
import com.resellerapp.repository.ConditionRepository;
import com.resellerapp.repository.OfferRepository;
import com.resellerapp.repository.UserRepository;
import com.resellerapp.service.OfferService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class OfferServiceImpl implements OfferService {

    private final UserRepository userRepository;
    private final OfferRepository offerRepository;
    private final ConditionRepository conditionRepository;

    public OfferServiceImpl(UserRepository userRepository, OfferRepository offerRepository, ConditionRepository conditionRepository) {
        this.userRepository = userRepository;
        this.offerRepository = offerRepository;
        this.conditionRepository = conditionRepository;
    }

    @Override
    public void add(OffersAddBindingModel offersAddBindingModel, String username) {

        Condition condition = conditionRepository.findByName(offersAddBindingModel.getCondition());

        if (condition != null) {
            Offer offer = new Offer();

            offer.setDescription(offersAddBindingModel
                    .getDescription())
                    .setPrice(BigDecimal.valueOf(offersAddBindingModel.getPrice()))
                    .setCondition(condition)
                    .setCreator(userRepository.findByUsername(username));

            offerRepository.save(offer);
        }

    }

    @Override
    public void remove(Long id) {
        offerRepository.deleteById(id);

    }

    @Override
    public void assign(Long id, String username) {
        Optional<Offer> optionalTask = offerRepository.findById(id);


        if (optionalTask.isPresent()) {
            Offer offer = optionalTask.get();

            if (username == null) {
                offer.setUser(null);
            } else {
                User user = userRepository.findByUsername(username);
                offer.setUser(user);
            }

            offerRepository.save(offer);
        }

    }

    @Override
    public OfferHomeViewModel getHomeViewData(String username) {
        User user = userRepository.findByUsername(username);

        List<OfferDto> ownOffers = offerRepository.findAllByCreator(user).stream().map(OfferDto::createFromOffer).toList();
        List<OfferDto> availableOffers = offerRepository.getAllAvailable().stream().filter(offer -> {
            if (offer.getCreator().getId() == user.getId()) {
                return false;
            }else {
                return true;

            }
        }).map(OfferDto::createFromOffer).toList();
        List<OfferDto> boughtOffers = offerRepository.findAllByUser(user).stream().map(OfferDto::createFromOffer).toList();



        return new OfferHomeViewModel(availableOffers, ownOffers, boughtOffers);
    }
}
