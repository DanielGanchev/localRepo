package org.softuni.service;

import org.softuni.model.dto.UserRegistrationDto;
import org.softuni.model.entities.UserEntity;
import org.softuni.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;


        this.passwordEncoder = passwordEncoder;
    }




    @Override
    public void register(UserRegistrationDto userRegistrationDto) {
        userRepository.save(map(userRegistrationDto));


    }

    @Override
    public void login(String email, String password) {

    }

    private  UserEntity map(UserRegistrationDto userRegistrationDto){
            return new UserEntity()
                    .setActive(true)
                    .setFirstName(userRegistrationDto.firstName())
                    .setLastName(userRegistrationDto.lastName())
                    .setEmail(userRegistrationDto.email())
                    .setPassword(passwordEncoder.encode(userRegistrationDto.password()));
        }
    }
