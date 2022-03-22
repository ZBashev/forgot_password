package com.example.forgotPassword.sevices;


import com.example.forgotPassword.models.entities.UserEntity;
import com.example.forgotPassword.models.servicesModels.UserLoginServiceModel;
import com.example.forgotPassword.models.servicesModels.UserRegisterServiceModel;
import org.springframework.ui.Model;

import java.util.Optional;

public interface UserService {
    void addUserRegister(UserRegisterServiceModel userRegisterServiceModel);

    void addUserLogin(UserLoginServiceModel userLoginServiceModel);

    boolean existingUsername(String username);

    UserEntity findUserByName(String username);

    boolean isAnonymousUser();

    boolean userIsNotAdmin();


    UserEntity getUserByAuthentication();







    Optional<UserEntity> findUserByEmail(String email);

    void save(UserEntity user);
}
