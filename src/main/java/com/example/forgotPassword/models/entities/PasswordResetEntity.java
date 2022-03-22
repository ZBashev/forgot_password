package com.example.forgotPassword.models.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "password_reset_token")
public class PasswordResetEntity extends BaseEntity {


    private static final int EXPIRATION = 60 * 24;
    private String token;
    private UserEntity userEntity;
    private Date expiryDate;


    public PasswordResetEntity() {

    }

    @Column(name = "token")
    public String getToken() {
        return token;
    }

    public PasswordResetEntity setToken(String token) {
        this.token = token;
        return this;
    }

    @OneToOne(targetEntity = UserEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    public UserEntity getUserEntity() {
        return userEntity;
    }

    public PasswordResetEntity setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
        return this;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public PasswordResetEntity setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
        return this;
    }
}
