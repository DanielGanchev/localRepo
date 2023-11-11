package org.softuni.service;

import org.softuni.model.entities.UserActivationCodeEntity;
import org.softuni.model.events.UserRegisteredEvent;
import org.softuni.repository.ActivationCodeRepository;
import org.softuni.repository.UserRepository;
import org.softuni.service.exception.ObjectNotFoundException;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Random;

@Service
public class UserActivationServiceImpl implements UserActivationService  {

    private static final String ACTIVATION_CODE = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private static final int ACTIVATION_CODE_LENGTH = 20;


    private final EmailService emailService;
    private final UserRepository userRepository;

    private final ActivationCodeRepository activationCodeRepository;

    public UserActivationServiceImpl(EmailService emailService, UserRepository userRepository, ActivationCodeRepository activationCodeRepository) {
        this.emailService = emailService;
        this.userRepository = userRepository;
        this.activationCodeRepository = activationCodeRepository;
    }


    @Override
    @EventListener(UserRegisteredEvent.class)
    public void userRegistered(UserRegisteredEvent userRegisteredEvent) {

       String activationCode = createActivationCode(userRegisteredEvent.getEmail());
        emailService.sendRegistrationEmail(userRegisteredEvent.getEmail(),userRegisteredEvent.getUserNames(),activationCode);


    }

    @Override
    public String createActivationCode(String email) {

        String activationCode = generateActivationCode();

        UserActivationCodeEntity userActivationCodeEntity = new UserActivationCodeEntity();

        userActivationCodeEntity.setActivationCode(activationCode);
        userActivationCodeEntity.setCreated(LocalDateTime.now());
        userActivationCodeEntity.setUser(userRepository.findByEmail(email).orElseThrow(() -> new ObjectNotFoundException("User not found!")));

        activationCodeRepository.save(userActivationCodeEntity);

        return activationCode;

    }

    private static String generateActivationCode() {
        Random random = new SecureRandom();
        StringBuilder activationCode = new StringBuilder();
        for (int i = 0; i < ACTIVATION_CODE_LENGTH; i++) {
            activationCode.append(ACTIVATION_CODE.charAt(random.nextInt(ACTIVATION_CODE.length())));
        }
        return activationCode.toString();
    }

}
