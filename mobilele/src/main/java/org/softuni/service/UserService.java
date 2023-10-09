package org.softuni.service;

import org.softuni.model.dto.UserLoginDto;
import org.softuni.model.dto.UserRegistrationDto;

public interface UserService {
    void register(UserRegistrationDto userRegistrationDto);

    boolean loginUser(UserLoginDto userLoginDto);

    void logoutUser();
}
