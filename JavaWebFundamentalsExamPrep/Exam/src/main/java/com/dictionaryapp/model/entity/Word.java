package com.dictionaryapp.model.entity;

import jakarta.persistence.*;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;


@Entity
@Table(name = "words")
public class Word extends BaseEntity{


    @Column(nullable = false)
    @Length(min = 2, max = 40)
    private String term;


    @Column(nullable = false)
    @Length(min = 2, max = 80)
    private String translation;

    @Column(nullable = false)
    @Length(min = 2, max = 200)
    private String example;


    @PastOrPresent
    @Column(nullable = false)
    private LocalDate inputDate;

   @ManyToOne
    private Language language;

    @ManyToOne
    private User addedBy;

    public String getTerm() {
        return term;
    }

    public Word setTerm(String term) {
        this.term = term;
        return this;
    }

    public String getTranslation() {
        return translation;
    }

    public Word setTranslation(String translation) {
        this.translation = translation;
        return this;
    }

    public String getExample() {
        return example;
    }

    public Word setExample(String example) {
        this.example = example;
        return this;
    }

    public LocalDate getInputDate() {
        return inputDate;
    }

    public Word setInputDate(LocalDate inputDate) {
        this.inputDate = inputDate;
        return this;
    }

    public Language getLanguage() {
        return language;
    }

    public Word setLanguage(Language language) {
        this.language = language;
        return this;
    }

    public User getAddedBy() {
        return addedBy;
    }

    public Word setAddedBy(User addedBy) {
        this.addedBy = addedBy;
        return this;
    }
}
