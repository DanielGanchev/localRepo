package org.softuni.web;

import jakarta.validation.Valid;

import org.softuni.model.dto.OfferDetailDTO;
import org.softuni.model.entities.enums.EngineEnum;
import org.softuni.model.entities.enums.TransmissionEnum;
import org.softuni.service.BrandService;
import org.softuni.service.OfferService;
import org.softuni.service.exception.ObjectNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.softuni.model.dto.CreateOfferDto;

import java.util.UUID;

@Controller
@RequestMapping("/offer")
public class OfferController {


    private final OfferService offerService;
    private final BrandService brandService;

    public OfferController(OfferService offerService, BrandService brandService){

        this.offerService = offerService;
        this.brandService = brandService;
    }




    @ModelAttribute("engines")
    public EngineEnum[] engines(){
        return EngineEnum.values();
    }

    @ModelAttribute("transmission")
    public TransmissionEnum[] transmission(){

        return TransmissionEnum.values();
    }

    @GetMapping("/add")
    public String add(Model model) {

        if (!model.containsAttribute("createOfferDto")) {
            model.addAttribute("createOfferDto", CreateOfferDto.empty());
        }

        model.addAttribute("brands", brandService.getAllBrands());

        return "offer-add";
    }

    @PostMapping("/add")
    public String add(
            @Valid CreateOfferDto createOfferDto,
            BindingResult bindingResult,
            RedirectAttributes rAtt) {

        if(bindingResult.hasErrors()){
            rAtt.addFlashAttribute("createOfferDto", createOfferDto);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.createOfferDto", bindingResult);
            return "redirect:/offer/add";
        }


        UUID newOfferUUID = offerService.createOffer(createOfferDto);

        return "redirect:/offer/" + newOfferUUID;
    }

    @PostMapping
    public String add(@Valid CreateOfferDto createOfferDto){

        offerService.createOffer(createOfferDto);

        return "index";
    }

    @GetMapping("/{uuid}")
    public String details(@PathVariable("uuid") UUID uuid, Model model){

        OfferDetailDTO offerDetailDTO = offerService
                .getOfferDetails(uuid)
                .orElseThrow(() ->new ObjectNotFoundException("Offer with uuid " + uuid + " not found"));;

                model.addAttribute("offer", offerDetailDTO);

        return "details";
    }

    @DeleteMapping ("/{uuid}")
    public String delete(@PathVariable("uuid") UUID uuid){

        offerService.deleteOffer(uuid);

        return "redirect:/offers/all";
    }




}
