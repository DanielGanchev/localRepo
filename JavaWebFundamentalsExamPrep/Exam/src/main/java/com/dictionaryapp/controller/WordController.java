package com.dictionaryapp.controller;


import com.dictionaryapp.model.dto.WordsAddBindingModel;
import com.dictionaryapp.service.WordService;
import com.dictionaryapp.service.impl.LoggedUser;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;

@Controller
public class WordController {

    private final WordService wordService;
    private final LoggedUser loggedUser;

    public WordController(WordService wordService, LoggedUser loggedUser) {
        this.wordService = wordService;
        this.loggedUser = loggedUser;
    }

    @GetMapping("/word/add")
    public ModelAndView add(@ModelAttribute("wordsAddBindingModel") WordsAddBindingModel wordsAddBindingModel) {
        if (!loggedUser.isLogged()) {
            return new ModelAndView("redirect:/");
        }

        return new ModelAndView("word-add");
    }

    @PostMapping("/word/add")
    public ModelAndView add(
            @ModelAttribute("wordsAddBindingModel") @Valid WordsAddBindingModel wordsAddBindingModel,
            BindingResult bindingResult) {
        if (!loggedUser.isLogged()) {
            return new ModelAndView("redirect:/");
        }

        if (bindingResult.hasErrors()) {
            return new ModelAndView("word-add");
        }

        wordService.add(wordsAddBindingModel,loggedUser.getUsername());

        return new ModelAndView("redirect:/home");
    }





    @PostMapping("/word/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id) {
        if (!loggedUser.isLogged()) {
            return new ModelAndView("redirect:/");
        }

        wordService.remove(id);

        return new ModelAndView("redirect:/home");
    }

    @PostMapping("/word/remove")
    public ModelAndView removeAll() {
        if (!loggedUser.isLogged()) {
            return new ModelAndView("redirect:/");
        }

        wordService.removeAll();

        return new ModelAndView("redirect:/home");
    }
}
