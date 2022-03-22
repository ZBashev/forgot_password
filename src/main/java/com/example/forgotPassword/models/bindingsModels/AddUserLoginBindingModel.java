package com.example.forgotPassword.models.bindingsModels;

import javax.validation.constraints.Size;

public class AddUserLoginBindingModel {

    private String username;
    private String password;


    public AddUserLoginBindingModel() {
    }

    @Size(min = 3)
    public String getUsername() {
        return username;
    }

    public AddUserLoginBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }

    @Size(min = 3)
    public String getPassword() {
        return password;
    }

    public AddUserLoginBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }
}
