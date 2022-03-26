package com.example.forgotPassword.dto;

import javax.validation.constraints.Size;

public class NewPasswordDto {


    private String newPassword;
    private String confirmPassword;



    public NewPasswordDto() {
    }

    @Size(min = 3)
    public String getNewPassword() {
        return newPassword;
    }

    public NewPasswordDto setNewPassword(String newPassword) {
        this.newPassword = newPassword;
        return this;
    }

    @Size(min = 3)
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public NewPasswordDto setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }


}
