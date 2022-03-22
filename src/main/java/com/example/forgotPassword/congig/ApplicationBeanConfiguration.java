package com.example.forgotPassword.congig;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.mail.MessagingException;
import java.util.Properties;







@Configuration
public class ApplicationBeanConfiguration {










// TODO class RestConfig

        @Bean
        public ModelMapper modelMapper() {
            return new ModelMapper();
        }


        @Bean
        public PasswordEncoder create() {

            return new BCryptPasswordEncoder();
        }

        @Bean
        public JavaMailSender mailSender() throws MessagingException {
            JavaMailSenderImpl sendEmail = new JavaMailSenderImpl();


            sendEmail.setUsername("kasparitaly@gmail.com");
            sendEmail.setPassword("foryudccwsmdfzci");

            sendEmail.setHost("smtp.gmail.com");
            sendEmail.setPort(587);


            Properties props = sendEmail.getJavaMailProperties();

            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.debug", "true");

            return sendEmail;

        }

    }

