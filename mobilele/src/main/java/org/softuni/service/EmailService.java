package org.softuni.service;

public interface EmailService {

    void sendRegistrationEmail(String email,String userNames,String activationCode);
}
