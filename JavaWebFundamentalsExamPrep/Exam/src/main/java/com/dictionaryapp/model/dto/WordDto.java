package com.dictionaryapp.model.dto;

import com.dictionaryapp.model.entity.Language;
import com.dictionaryapp.model.entity.LanguageName;
import com.dictionaryapp.model.entity.Word;

public class WordDto {

        private Long id;
        private String term;
        private String translation;
        private String example;
        private String inputDate;

        private String username;

    public String getUsername() {
        return username;
    }

    public WordDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public Long getId() {
        return id;
    }

    public WordDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTerm() {
        return term;
    }

    public WordDto setTerm(String term) {
        this.term = term;
        return this;
    }

    public String getTranslation() {
        return translation;
    }

    public WordDto setTranslation(String translation) {
        this.translation = translation;
        return this;
    }

    public String getExample() {
        return example;
    }

    public WordDto setExample(String example) {
        this.example = example;
        return this;
    }

    public String getInputDate() {
        return inputDate;
    }

    public WordDto setInputDate(String inputDate) {
        this.inputDate = inputDate;
        return this;
    }

    public static WordDto createFromWord(Word word) {
        WordDto wordDto = new WordDto();

        wordDto.setId(word.getId());
        wordDto.setTerm(word.getTerm());
        wordDto.setTranslation(word.getTranslation());
        wordDto.setExample(word.getExample());
        wordDto.setInputDate(word.getInputDate().toString());
        wordDto.setUsername(word.getAddedBy().getUsername());


        return wordDto;
    }
}
