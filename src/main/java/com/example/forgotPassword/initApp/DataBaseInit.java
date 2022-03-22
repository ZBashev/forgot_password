package com.example.forgotPassword.initApp;


import com.example.forgotPassword.sevices.UserRoleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataBaseInit implements CommandLineRunner {


    private final UserRoleService userRoleService;

    public DataBaseInit(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }


    @Override
    public void run(String... args) throws Exception {

        this.userRoleService.initRole();


    }
}
