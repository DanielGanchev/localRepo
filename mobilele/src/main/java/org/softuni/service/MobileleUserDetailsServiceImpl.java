package org.softuni.service;

import org.softuni.model.entities.UserEntity;
import org.softuni.model.entities.UserRoleEntity;
import org.softuni.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;


public class MobileleUserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public MobileleUserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       return userRepository.findByEmail(email)
               .map(MobileleUserDetailsServiceImpl::map)
               .orElseThrow(() ->
                       new UsernameNotFoundException("User " + email + " not found!"));

    }

    private static UserDetails map(UserEntity userEntity){
      return   User.withUsername(userEntity.getEmail())
                .password(userEntity.getPassword())
                .authorities(userEntity.getRoles().stream().map( MobileleUserDetailsServiceImpl::map).toList())
                .build();


    }

    private static GrantedAuthority map(UserRoleEntity role) {
        return new SimpleGrantedAuthority("ROLE_" + role.getRole().name()
        );
    }
}
