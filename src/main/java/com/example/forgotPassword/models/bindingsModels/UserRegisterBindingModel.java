package com.example.forgotPassword.models.bindingsModels;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


public class UserRegisterBindingModel {

    private String username;
    private String fullName;
    private String password;
    private String email;

    public UserRegisterBindingModel() {
    }


    @Size(min = 3, message = "The user name must be minimum 8 characters")
    @NotBlank(message = "The user name cannot be EMPTY !!!")
    public String getUsername() {
        return username;
    }

    public UserRegisterBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }

    @Size(min = 3, message = "The field must be minimum 3 characters")

    @NotBlank(message = "The field cannot be EMPTY !!!")
    public String getFullName() {
        return fullName;
    }

    public UserRegisterBindingModel setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }


    //todo make password min = 8
    @Size(min = 3, max = 20, message = "The password must be minimum 8 characters !!!")
    @NotBlank(message = "The PASSWORD cannot be empty !!!")
    public String getPassword() {
        return password;
    }

    public UserRegisterBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }

    //todo pattern
    @Email(message = "Enter valid email address !!!")
    @NotBlank(message = "Please provide an e-mail")
    public String getEmail() {
        return email;
    }

    public UserRegisterBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }
}
