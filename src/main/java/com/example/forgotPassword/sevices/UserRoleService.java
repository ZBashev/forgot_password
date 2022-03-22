package com.example.forgotPassword.sevices;


import com.example.forgotPassword.models.entities.UserRoleEntity;
import com.example.forgotPassword.models.enumerations.UserRoleEnumerations;

public interface UserRoleService {

    void initRole();

    UserRoleEntity findRole(UserRoleEnumerations enumerations);

}
