package com.dictionaryapp.model.dto;



import com.dictionaryapp.model.entity.Language;
import com.dictionaryapp.model.entity.LanguageName;
import com.dictionaryapp.validation.StringDateInThePastOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;


public class WordsAddBindingModel {


    @Size(min = 2, max = 40 ,message = "The term length must be between 2 and 40 characters!")
    private String term;



    @Size(min = 2, max = 80, message = "The translation length must be between 2 and 80 characters!")
    private String translation;


    @Size(min = 2, max = 200 , message = "The example length must be between 2 and 200 characters!")
    private String example;



    @StringDateInThePastOrPresent(message = "The input date must be in the past or present!")

    private String inputDate;

    @NotNull(message = "You must select a language!")
    private LanguageName language;



    public String getTerm() {
        return term;
    }

    public WordsAddBindingModel setTerm(String term) {
        this.term = term;
        return this;
    }

    public String getTranslation() {
        return translation;
    }

    public WordsAddBindingModel setTranslation(String translation) {
        this.translation = translation;
        return this;
    }

    public String getExample() {
        return example;
    }

    public WordsAddBindingModel setExample(String example) {
        this.example = example;
        return this;
    }

    public String getInputDate() {
        return inputDate;
    }

    public WordsAddBindingModel setInputDate(String inputDate) {
        this.inputDate = inputDate;
        return this;
    }

    public LanguageName getLanguage() {
        return language;
    }

    public WordsAddBindingModel setLanguage(LanguageName language) {
        this.language = language;
        return this;
    }
}
