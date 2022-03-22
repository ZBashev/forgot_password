package com.example.forgotPassword.sevices.impl;


import com.example.forgotPassword.models.entities.UserEntity;
import com.example.forgotPassword.sevices.EmailService;
import com.example.forgotPassword.sevices.UserService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmailServiceImpl implements EmailService {


    private final JavaMailSender mailSender;
    private final UserService userService;


    public EmailServiceImpl(JavaMailSender mailSender, UserService userService) {
        this.mailSender = mailSender;
        this.userService = userService;
    }


    @Override
    @Async
    public void sendEmail(SimpleMailMessage email) {


        this.mailSender.send(email);

    }

    @Override
    public String sendRequestResetPassword(HttpServletRequest request, String email) {

        Optional<UserEntity> optionalUser = this.userService.findUserByEmail(email);


        UserEntity user = optionalUser.get();

        user.setResetToken(UUID.randomUUID().toString());

        this.userService.save(user);


        String appUrl = request.getScheme() + "://" + request.getServerName();
        SimpleMailMessage passwordResteMail = new SimpleMailMessage();

        passwordResteMail.setFrom("kasparitaly@gmail.com");
        passwordResteMail.setTo(user.getEmail());
        passwordResteMail.setSubject("Password Reset Request");
        passwordResteMail.setText("The new password : " + appUrl + ":8080/reset?token=" + user.getResetToken());



        this.sendEmail(passwordResteMail);


        return email;
    }
}
