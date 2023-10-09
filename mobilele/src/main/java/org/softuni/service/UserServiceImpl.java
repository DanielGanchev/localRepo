package org.softuni.service;

import org.softuni.model.dto.UserLoginDto;
import org.softuni.model.dto.UserRegistrationDto;
import org.softuni.model.entities.UserEntity;
import org.softuni.repository.UserRepository;
import org.softuni.util.CurrentUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CurrentUser currentUser;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, CurrentUser currentUser) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.currentUser = currentUser;
    }




    @Override
    public void register(UserRegistrationDto userRegistrationDto) {
        userRepository.save(map(userRegistrationDto));


    }

    @Override
    public boolean loginUser(UserLoginDto userLoginDto) {
       var userEntity = userRepository
               .findByEmail(userLoginDto.email())
               .orElse(null);

      boolean logicSuccess = false;
      if (userEntity != null){
          String rawPassword = userLoginDto.password();
          String encodedPassword = userEntity.getPassword();

          logicSuccess = encodedPassword != null && passwordEncoder.matches(rawPassword,encodedPassword);

          if (logicSuccess){
              currentUser
                      .setLogged(true)
                      .setFirstName(userEntity.getFirstName())
                      .setLastName(userEntity.getLastName());
          }else {
              currentUser.logout();
          }
      }
      return logicSuccess;
    }

    @Override
    public void logoutUser() {
        currentUser.logout();

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
