package org.softuni.service;

import org.softuni.model.dto.UserRegistrationDto;

public interface UserService {
    void register(UserRegistrationDto userRegistrationDto);

    void login(String email, String password);
}
