package com.dictionaryapp.service;

import com.dictionaryapp.model.dto.UserLoginBindingModel;
import com.dictionaryapp.model.dto.UserRegisterBindingModel;

public interface UserService {
    boolean login(UserLoginBindingModel userLoginBindingModel);

    boolean register(UserRegisterBindingModel userRegisterBindingModel);

    void logout();
}
