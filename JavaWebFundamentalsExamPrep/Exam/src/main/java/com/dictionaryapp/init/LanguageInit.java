package com.dictionaryapp.init;

import com.dictionaryapp.model.entity.Language;
import com.dictionaryapp.model.entity.LanguageName;
import com.dictionaryapp.repo.LanguageRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Component
public class LanguageInit implements CommandLineRunner {

    private final LanguageRepository languageRepository;

    public LanguageInit(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        long count = languageRepository.count();

        if (count == 0) {
            List<Language> conditions = new ArrayList<>();

            Arrays.stream(LanguageName.values())
                    .forEach(conditionName -> {
                        Language language = new Language();
                        language.setName(conditionName);
                        conditions.add(language);
                    });

            languageRepository.saveAll(conditions);
        }

    }
}
