package com.example.forgotPassword.models.entities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;


@Entity
@Table(name = "users")

public class UserEntity extends BaseEntity {


    private String username;
    private String fullName;
    private String password;
    private String email;
    private String roleName;
    private UserRoleEntity userRole;

    private String resetToken;


    public UserEntity() {
    }


    @Column(name = "username", nullable = false, unique = true)
    public String getUsername() {
        return username;
    }

    public UserEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    @Column(name = "full_name", nullable = false)
    public String getFullName() {
        return fullName;
    }

    public UserEntity setFullName(String fullname) {
        this.fullName = fullname;
        return this;
    }

    @Column(name = "password", nullable = false, unique = true)
    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    @Column(name = "email", nullable = false, unique = true)
    @Email(message = "Please provide a valid e-mail")
    @NotEmpty(message = "Please provide an e-mail")
    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    @Column(name = "role_name")
    public String getRoleName() {
        return roleName;
    }

    public UserEntity setRoleName(String roleName) {
        this.roleName = roleName;
        return this;
    }

    @OneToOne
    @Enumerated(EnumType.STRING)
    public UserRoleEntity getUserRole() {
        return userRole;
    }

    public UserEntity setUserRole(UserRoleEntity userRole) {
        this.userRole = userRole;
        return this;
    }








    public String getResetToken() {
        return resetToken;
    }

    public UserEntity setResetToken(String resetToken) {
        this.resetToken = resetToken;
        return this;
    }
}
