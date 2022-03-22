package com.example.forgotPassword.sevices;

import org.springframework.mail.SimpleMailMessage;

import javax.servlet.http.HttpServletRequest;


public interface EmailService {


    void sendEmail(SimpleMailMessage email);


    String sendRequestResetPassword(HttpServletRequest request, String email);
}
