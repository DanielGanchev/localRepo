package com.plannerapp.model.dto.user;

import com.plannerapp.model.entity.Task;
import com.plannerapp.model.entity.User;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.validation.constraints.*;
import java.util.Set;

public class UserRegisterBindingModel {

    @Size(min = 3, max = 20, message = "Username length must be between 3 and 20 characters!")

    private String username;

    @Email
    @NotBlank(message = "Email cannot be empty!")
    private String email;

    @Size(min = 3, max = 20 ,message = "Password length must be between 3 and 20 characters!")

    private String password;



    private String confirmPassword;

    public String getUsername() {
        return username;
    }

    public UserRegisterBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegisterBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegisterBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegisterBindingModel setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
}
