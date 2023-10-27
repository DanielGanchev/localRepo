package com.dictionaryapp.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

@Entity
@Table(name = "languages")
public class Language extends BaseEntity {

    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private LanguageName name;

    @NotNull
    private String description;

    @OneToMany (mappedBy = "language")
    private Set<Word> words;

    private void setDescription(LanguageName name){
        String description = "";

        switch (name) {
            case ITALIAN:
                description = "A Romance language spoken in Italy and parts of Switzerland, with rich cultural heritage. Known for its melodious sounds, it's a gateway to Italian art, cuisine, and history.";
                break;
            case GERMAN:
                description = "A West Germanic language, is spoken by over 90 million people worldwide. Known for its complex grammar and compound words, it's the official language of Germany and widely used in Europe.";
                break;
            case FRENCH:
                description = "A Romance language spoken worldwide, known for its elegance and cultural richness. It's the official language of France and numerous nations, famed for its cuisine, art, and literature.";
                break;
            case SPANISH:
                description = "A Romance language, is spoken by over 460 million people worldwide. It boasts a rich history, diverse dialects, and is known for its melodious sound, making it a global cultural treasure.";
                break;
        }

        this.description = description;
    }

    public void setName(LanguageName name) {
        this.name = name;
        setDescription(name);
    }

    public LanguageName getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }



    public Set<Word> getWords() {
        return words;
    }

    public Language setWords(Set<Word> words) {
        this.words = words;
        return this;
    }


}
