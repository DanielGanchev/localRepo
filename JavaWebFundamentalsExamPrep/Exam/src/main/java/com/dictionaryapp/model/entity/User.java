package com.dictionaryapp.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.repository.cdi.Eager;

import java.util.Set;

@Entity
@Table(name = "user")
public class User extends BaseEntity {

    @Length(min = 3, max = 20)
    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Email
    @Column(unique = true, nullable = false)
    private String email;



    @OneToMany(mappedBy = "addedBy",fetch = FetchType.EAGER)

    private Set<Word> addedWords;


    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public Set<Word> getAddedWords() {
        return addedWords;
    }

    public User setAddedWords(Set<Word> addedWords) {
        this.addedWords = addedWords;
        return this;
    }
}

