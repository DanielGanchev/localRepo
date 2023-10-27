package com.dictionaryapp.model.dto;

import java.util.ArrayList;
import java.util.List;

public class WordHomeViewModel {

    private List<WordDto> german;
    private List<WordDto> spanish;
    private List<WordDto> french;
    private List<WordDto> italian;

    private int germanSize;
    private int spanishSize;
    private int frenchSize;
    private int italianSize;

    private int allSize;

    public int getAllSize() {
        return allSize;
    }

    public WordHomeViewModel setAllSize(int allSize) {
        this.allSize = allSize;
        return this;
    }

    public WordHomeViewModel() {
        this(new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    }

    public WordHomeViewModel(List<WordDto> german, List<WordDto> spanish, List<WordDto> french, List<WordDto> italian) {
        this.german = german;
        this.spanish = spanish;
        this.french = french;
        this.italian = italian;
        this.germanSize = german.size();
        this.spanishSize = spanish.size();
        this.frenchSize = french.size();
        this.italianSize = italian.size();
        this.allSize = germanSize + spanishSize + frenchSize + italianSize;
    }

    public List<WordDto> getGerman() {
        return german;
    }

    public WordHomeViewModel setGerman(List<WordDto> german) {
        this.german = german;
        return this;
    }

    public List<WordDto> getSpanish() {
        return spanish;
    }

    public WordHomeViewModel setSpanish(List<WordDto> spanish) {
        this.spanish = spanish;
        return this;
    }

    public List<WordDto> getFrench() {
        return french;
    }

    public WordHomeViewModel setFrench(List<WordDto> french) {
        this.french = french;
        return this;
    }

    public List<WordDto> getItalian() {
        return italian;
    }

    public WordHomeViewModel setItalian(List<WordDto> italian) {
        this.italian = italian;
        return this;
    }

    public int getGermanSize() {
        return germanSize;
    }

    public WordHomeViewModel setGermanSize(int germanSize) {
        this.germanSize = germanSize;
        return this;
    }

    public int getSpanishSize() {
        return spanishSize;
    }

    public WordHomeViewModel setSpanishSize(int spanishSize) {
        this.spanishSize = spanishSize;
        return this;
    }

    public int getFrenchSize() {
        return frenchSize;
    }

    public WordHomeViewModel setFrenchSize(int frenchSize) {
        this.frenchSize = frenchSize;
        return this;
    }

    public int getItalianSize() {
        return italianSize;
    }

    public WordHomeViewModel setItalianSize(int italianSize) {
        this.italianSize = italianSize;
        return this;
    }
}
