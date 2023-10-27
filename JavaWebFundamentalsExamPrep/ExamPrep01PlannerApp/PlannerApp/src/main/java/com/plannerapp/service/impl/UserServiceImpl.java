package com.plannerapp.service.impl;

import com.plannerapp.model.dto.user.UserLoginBindingModel;
import com.plannerapp.model.dto.user.UserRegisterBindingModel;
import com.plannerapp.model.entity.User;
import com.plannerapp.repo.UserRepository;
import com.plannerapp.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final LoggedUser loggedUser;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.loggedUser = loggedUser;
    }


    @Override
    public boolean register(UserRegisterBindingModel userRegisterBindingModel) {

        if (!userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {
            return false;
        }

        boolean isUsernameFree = userRepository.existsByUsernameOrEmail(userRegisterBindingModel.getUsername(), userRegisterBindingModel.getEmail());

        if (isUsernameFree) {
            return false;
        }

        User userEntity = new User();

        userEntity.setEmail(userRegisterBindingModel.getEmail()).setUsername(userRegisterBindingModel.getUsername())
                .setPassword(passwordEncoder.encode(userRegisterBindingModel.getPassword()));

        userRepository.save(userEntity);


        return true;

    }

    @Override
    public boolean login(UserLoginBindingModel userLoginBindingModel) {

        User userEntity = userRepository.findByUsername(userLoginBindingModel.getUsername());

        if (userEntity != null && passwordEncoder.matches(userLoginBindingModel.getPassword(), userEntity.getPassword())) {
            loggedUser.login(userEntity.getUsername());
            return true;
        }


        return false;
    }

    @Override
    public void logout() {
        this.loggedUser.logout();
    }
}
