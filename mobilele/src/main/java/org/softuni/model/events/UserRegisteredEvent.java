package org.softuni.model.events;

import org.springframework.context.ApplicationEvent;

public class UserRegisteredEvent extends ApplicationEvent {
    private final String email;
    private final String userNames;

    public UserRegisteredEvent(Object source , String email,String userNames
    ) {
        super(source);
        this.email = email;
        this.userNames = userNames;
    }

    public String getEmail() {
        return email;
    }

    public String getUserNames() {
        return userNames;
    }
}
