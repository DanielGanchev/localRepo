package org.softuni.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

@Entity(name = "user_activation_codes")
public class UserActivationCodeEntity extends BaseEntity{

    private String activationCode;

    private LocalDateTime created;

    @ManyToOne
    private UserEntity user;

    public String getActivationCode() {
        return activationCode;
    }

    public UserActivationCodeEntity setActivationCode(String activationCode) {
        this.activationCode = activationCode;
        return this;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public UserActivationCodeEntity setCreated(LocalDateTime created) {
        this.created = created;
        return this;
    }

    public UserEntity getUser() {
        return user;
    }

    public UserActivationCodeEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }
}
