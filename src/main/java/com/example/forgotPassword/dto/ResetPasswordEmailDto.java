package com.example.forgotPassword.dto;

import javax.validation.constraints.Email;

public class ResetPasswordEmailDto {

    private String email;


    public ResetPasswordEmailDto() {
    }

    @Email(message = "Diese Email existiert nicht.")
    public String getEmail() {
        return email;
    }

    public ResetPasswordEmailDto setEmail(String email) {
        this.email = email;
        return this;
    }
}



