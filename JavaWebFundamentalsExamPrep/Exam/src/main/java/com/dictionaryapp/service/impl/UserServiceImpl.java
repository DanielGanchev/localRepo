package com.dictionaryapp.service.impl;

import com.dictionaryapp.model.dto.UserLoginBindingModel;
import com.dictionaryapp.model.dto.UserRegisterBindingModel;
import com.dictionaryapp.model.entity.User;
import com.dictionaryapp.repo.UserRepository;
import com.dictionaryapp.service.UserService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {


    private final LoggedUser loggedUser;
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(LoggedUser loggedUser, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.loggedUser = loggedUser;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean login(UserLoginBindingModel userLoginBindingModel) {
        String username = userLoginBindingModel.getUsername();
        User user = userRepository.findByUsername(username);

        if (user != null
                && passwordEncoder.matches(userLoginBindingModel.getPassword(), user.getPassword())) {
            loggedUser.login(username);
            return true;
        }else

    return false;

    }

    @Override
    public boolean register(UserRegisterBindingModel userRegisterBindingModel) {
        if (!userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {
            return false;
        }

        boolean existsByUsernameOrEmail = userRepository.existsByUsernameOrEmail(
                userRegisterBindingModel.getUsername(),
                userRegisterBindingModel.getEmail());

        if (existsByUsernameOrEmail) {
            return false;
        }

        User user = new User();
        user.setUsername(userRegisterBindingModel.getUsername());
        user.setEmail(userRegisterBindingModel.getEmail());
        user.setPassword(passwordEncoder.encode(userRegisterBindingModel.getPassword()));

        userRepository.save(user);

        return true;
    }

    @Override
    public void logout() {
        this.loggedUser.logout();
    }
}
