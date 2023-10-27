package com.resellerapp.service;

import com.resellerapp.model.dto.UserLoginBindingModel;
import com.resellerapp.model.dto.UserRegisterBindingModel;

public interface UserService {
    boolean login(UserLoginBindingModel userLoginBindingModel);

    boolean register(UserRegisterBindingModel userRegisterBindingModel);

    void logout();
}
