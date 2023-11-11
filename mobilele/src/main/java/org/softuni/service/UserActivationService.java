package org.softuni.service;

import org.softuni.model.events.UserRegisteredEvent;

public interface UserActivationService {

    void userRegistered(UserRegisteredEvent userRegisteredEvent);

    String createActivationCode(String email);
}
