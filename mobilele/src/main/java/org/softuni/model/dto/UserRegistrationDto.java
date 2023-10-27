package org.softuni.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import validation.FieldMatch;
import validation.UniqueUserEmail;

@FieldMatch(first = "password", second = "confirmPassword" , message = "Passwords don't match!")
public record UserRegistrationDto(@NotEmpty String firstName,@NotEmpty String lastName, @NotNull @Email @UniqueUserEmail String email, String password, String confirmPassword) {
}
