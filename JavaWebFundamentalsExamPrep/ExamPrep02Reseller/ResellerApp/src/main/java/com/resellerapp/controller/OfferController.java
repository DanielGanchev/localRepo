package com.resellerapp.controller;

import com.resellerapp.model.dto.OffersAddBindingModel;
import com.resellerapp.service.OfferService;
import com.resellerapp.service.impl.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class OfferController {

    private final OfferService offerService;
    private final LoggedUser loggedUser;

    public OfferController(OfferService offerService, LoggedUser loggedUser) {
        this.offerService = offerService;
        this.loggedUser = loggedUser;
    }

    @GetMapping("/offer/add")
    public ModelAndView add(@ModelAttribute("offersAddBindingModel") OffersAddBindingModel offersAddBindingModel) {
        if (!loggedUser.isLogged()) {
            return new ModelAndView("redirect:/");
        }

        return new ModelAndView("offer-add");
    }

    @PostMapping("/offer/add")
    public ModelAndView add(
            @ModelAttribute("offersAddBindingModel") @Valid OffersAddBindingModel offersAddBindingModel,
            BindingResult bindingResult) {
        if (!loggedUser.isLogged()) {
            return new ModelAndView("redirect:/");
        }

        if (bindingResult.hasErrors()) {
            return new ModelAndView("offer-add");
        }

        offerService.add(offersAddBindingModel,loggedUser.getUsername());

        return new ModelAndView("redirect:/home");
    }

    @PostMapping("/offer/return/{id}")
    public ModelAndView returnTask(@PathVariable("id") Long id) {
        if (!loggedUser.isLogged()) {
            return new ModelAndView("redirect:/");
        }

        offerService.assign(id, null);

        return new ModelAndView("redirect:/home");
    }

    @PostMapping("/offer/assign/{id}")
    public ModelAndView assign(@PathVariable("id") Long id) {
        if (!loggedUser.isLogged()) {
            return new ModelAndView("redirect:/");
        }

        offerService.assign(id, loggedUser.getUsername());

        return new ModelAndView("redirect:/home");
    }

    @PostMapping("/offer/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id) {
        if (!loggedUser.isLogged()) {
            return new ModelAndView("redirect:/");
        }

        offerService.remove(id);

        return new ModelAndView("redirect:/home");
    }


}
