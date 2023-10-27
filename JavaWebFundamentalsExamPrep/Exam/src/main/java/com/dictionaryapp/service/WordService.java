package com.dictionaryapp.service;

import com.dictionaryapp.model.dto.WordHomeViewModel;
import com.dictionaryapp.model.dto.WordsAddBindingModel;
import com.dictionaryapp.model.entity.Word;

import java.util.List;

public interface WordService {
    void add(WordsAddBindingModel wordsAddBindingModel, String username);

    WordHomeViewModel getHomeViewData(String username);

    void remove(Long id);


    void removeAll();
}
