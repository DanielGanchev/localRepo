package com.dictionaryapp.service.impl;

import com.dictionaryapp.model.dto.WordDto;
import com.dictionaryapp.model.dto.WordHomeViewModel;
import com.dictionaryapp.model.dto.WordsAddBindingModel;
import com.dictionaryapp.model.entity.Language;
import com.dictionaryapp.model.entity.User;
import com.dictionaryapp.model.entity.Word;
import com.dictionaryapp.repo.LanguageRepository;
import com.dictionaryapp.repo.UserRepository;
import com.dictionaryapp.repo.WordRepository;
import com.dictionaryapp.service.WordService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class WordServiceImpl implements WordService {

    private final WordRepository wordRepository;
    private final LanguageRepository languageRepository;

    private final UserRepository userRepository;

    public WordServiceImpl(WordRepository wordRepository, LanguageRepository languageRepository, UserRepository userRepository) {
        this.wordRepository = wordRepository;
        this.languageRepository = languageRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void add(WordsAddBindingModel wordsAddBindingModel, String username) {
        Language language = languageRepository.findByName(wordsAddBindingModel.getLanguage());
        User user = userRepository.findByUsername(username);
        if (language != null && user != null) {

            Word word = new Word();


            word.setTerm(wordsAddBindingModel.getTerm())
                    .setTranslation(wordsAddBindingModel.getTranslation())
                    .setExample(wordsAddBindingModel.getExample())
                    .setInputDate(LocalDate.parse(wordsAddBindingModel.getInputDate()))
                    .setLanguage(language)
                    .setAddedBy(user);


            user.getAddedWords().add(word);




            wordRepository.save(word);

        }
    }

    @Override
    public WordHomeViewModel getHomeViewData(String username) {
        User user = userRepository.findByUsername(username);

        if (user != null) {

            List<WordDto> germanWords = wordRepository.findAll().stream().filter(word -> String.valueOf(word.getLanguage().getName()) == "GERMAN").map(WordDto::createFromWord).toList();
            List<WordDto> spanishWords = wordRepository.findAll().stream().filter(word -> String.valueOf(word.getLanguage().getName()) == "SPANISH").map(WordDto::createFromWord).toList();
            List<WordDto> frenchWords = wordRepository.findAll().stream().filter(word -> String.valueOf(word.getLanguage().getName()) == "FRENCH").map(WordDto::createFromWord).toList();
            List<WordDto> italianWords = wordRepository.findAll().stream().filter(word -> String.valueOf(word.getLanguage().getName()) == "ITALIAN").map(WordDto::createFromWord).toList();

            return new WordHomeViewModel(germanWords, spanishWords, frenchWords, italianWords);
        }

        return null;

    }

    @Override
    public void remove(Long id) {

        wordRepository.deleteById(id);

    }

    @Override
    public void removeAll() {

        wordRepository.deleteAll();

    }
}
