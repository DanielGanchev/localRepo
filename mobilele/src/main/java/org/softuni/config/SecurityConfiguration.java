package org.softuni.config;

import org.softuni.model.entities.UserRoleEnum;
import org.softuni.repository.UserRepository;
import org.softuni.service.MobileleUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {


    private final String rememberMeKey;


    public SecurityConfiguration(@Value("${mobilele.remember.me.key}")
                                 String rememberMeKey) {
        this.rememberMeKey = rememberMeKey;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity.authorizeHttpRequests(
              authorizeRequests -> authorizeRequests
                      .requestMatchers(PathRequest.toStaticResources()
                              .atCommonLocations())
                      .permitAll()
                      .requestMatchers
                              ("/","/users/login", "/users/register","/users/login-error","/offers/all")
                      .permitAll()
                      .requestMatchers("/brands").hasRole(UserRoleEnum.ADMIN.name())
                      .anyRequest().authenticated()
      ).formLogin(
              formLogin -> {
                  formLogin
                          .loginPage("/users/login")
                          .usernameParameter("email")
                          .passwordParameter("password")
                          .defaultSuccessUrl("/", true)
                          .failureForwardUrl("/users/login-error");
              }

      ).logout(
              logout -> {
                  logout
                          .logoutUrl("/users/logout")
                          .logoutSuccessUrl("/")
                          .invalidateHttpSession(true);
              }
      ).rememberMe(
            rememberMe -> {
                rememberMe
                        .key(rememberMeKey)
                        .rememberMeParameter("rememberme")
                        .rememberMeCookieName("rememberme");
            }
    ).build();



    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new MobileleUserDetailsServiceImpl(userRepository);
    }

    @Bean
    public PasswordEncoder createPasswordEncoder(){
        return  Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    }
}
