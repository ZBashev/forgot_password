package com.example.forgotPassword.sevices.impl;


import com.example.forgotPassword.models.entities.UserRoleEntity;
import com.example.forgotPassword.models.enumerations.UserRoleEnumerations;
import com.example.forgotPassword.repositories.UserRoleRepository;
import com.example.forgotPassword.sevices.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository userRoleRepository;

    public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }


    @Override
    public void initRole() {


        if (this.userRoleRepository.count() == 0) {
            UserRoleEntity admin = new UserRoleEntity(UserRoleEnumerations.ADMIN);
            UserRoleEntity user = new UserRoleEntity(UserRoleEnumerations.USER);

            this.userRoleRepository.saveAll(List.of(admin, user));


        }

    }

    @Override
    public UserRoleEntity findRole(UserRoleEnumerations enumerations) {


        return this.userRoleRepository.findByRoleEnumerations(enumerations).orElse(null);
    }
}
