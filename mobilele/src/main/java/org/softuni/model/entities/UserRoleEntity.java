package org.softuni.model.entities;

import jakarta.persistence.*;
import org.softuni.model.entities.enums.UserRoleEnum;

@Entity
@Table(name = "user_role")
public class UserRoleEntity {


    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;


    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;

    public Long getId() {
        return id;
    }

    public UserRoleEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public UserRoleEnum getRole() {
        return role;
    }

    public UserRoleEntity setRole(UserRoleEnum role) {
        this.role = role;
        return this;
    }
}
