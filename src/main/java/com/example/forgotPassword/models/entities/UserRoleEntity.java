package com.example.forgotPassword.models.entities;


import com.example.forgotPassword.models.enumerations.UserRoleEnumerations;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class UserRoleEntity extends BaseEntity {


    private UserRoleEnumerations roleEnumerations;


    public UserRoleEntity() {
    }

    @Autowired
    public UserRoleEntity(UserRoleEnumerations roleEnumerations) {
        this.roleEnumerations = roleEnumerations;
    }

    @Column()
    @Enumerated(EnumType.STRING)
    public UserRoleEnumerations getRoleEnumerations() {
        return roleEnumerations;
    }

    public UserRoleEntity setRoleEnumerations(UserRoleEnumerations roleEnumerations) {
        this.roleEnumerations = roleEnumerations;
        return this;
    }
}
