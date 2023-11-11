package org.softuni.service;

import org.softuni.model.dto.UserRegistrationDto;
import org.softuni.model.entities.UserEntity;
import org.softuni.model.events.UserRegisteredEvent;
import org.softuni.repository.UserRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final ApplicationEventPublisher applicationEventPublisher;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, ApplicationEventPublisher applicationEventPublisher) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.applicationEventPublisher = applicationEventPublisher;
    }


    @Override
    public void register(UserRegistrationDto userRegistrationDto) {
        userRepository.save(map(userRegistrationDto));

        applicationEventPublisher.publishEvent(new UserRegisteredEvent("UserService", userRegistrationDto.email()));


    }


    private UserEntity map(UserRegistrationDto userRegistrationDto) {
        return new UserEntity()
                .setActive(false)
                .setFirstName(userRegistrationDto.firstName())
                .setLastName(userRegistrationDto.lastName())
                .setEmail(userRegistrationDto.email())
                .setPassword(passwordEncoder.encode(userRegistrationDto.password()));
    }
}
