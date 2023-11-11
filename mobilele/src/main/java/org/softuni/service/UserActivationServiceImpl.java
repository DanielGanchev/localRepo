package org.softuni.service;

import org.softuni.model.events.UserRegisteredEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class UserActivationServiceImpl implements UserActivationService  {


    @Override
    @EventListener(UserRegisteredEvent.class)
    public void userRegistered(UserRegisteredEvent userRegisteredEvent) {
        System.out.println("User registered: " + userRegisteredEvent.getEmail());

    }

}
