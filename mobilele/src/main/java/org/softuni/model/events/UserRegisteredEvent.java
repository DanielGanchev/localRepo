package org.softuni.model.events;

import org.springframework.context.ApplicationEvent;

public class UserRegisteredEvent extends ApplicationEvent {
    private final String email;

    public UserRegisteredEvent(Object source , String email) {
        super(source);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
