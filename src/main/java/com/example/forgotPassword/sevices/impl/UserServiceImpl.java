package com.example.forgotPassword.sevices.impl;


import com.example.forgotPassword.models.entities.UserEntity;
import com.example.forgotPassword.models.enumerations.UserRoleEnumerations;
import com.example.forgotPassword.models.servicesModels.UserLoginServiceModel;
import com.example.forgotPassword.models.servicesModels.UserRegisterServiceModel;
import com.example.forgotPassword.repositories.UserRepository;
import com.example.forgotPassword.security.BoilSecurityDetailService;
import com.example.forgotPassword.sevices.UserRoleService;
import com.example.forgotPassword.sevices.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleService userRoleService;
    private final BoilSecurityDetailService boilSecurityDetailService;


    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder,
                           UserRoleService userRoleService, BoilSecurityDetailService boilSecurityDetailService
    ) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.userRoleService = userRoleService;
        this.boilSecurityDetailService = boilSecurityDetailService;
    }


    @Override
    public void addUserRegister(UserRegisterServiceModel userRegisterServiceModel) {
//TODO email is UNIQUE !!!
        UserEntity userEntity = this.modelMapper.map(userRegisterServiceModel, UserEntity.class);
        userEntity.setPassword(this.passwordEncoder.encode(userRegisterServiceModel.getPassword()));

        if (this.userRepository.count() == 0) {

            userEntity.setUserRole(this.userRoleService.findRole(UserRoleEnumerations.ADMIN));
            userEntity.setRoleName(
                    this.userRoleService.findRole(UserRoleEnumerations.ADMIN).getRoleEnumerations().name());
        } else {

            userEntity.setUserRole(this.userRoleService.findRole(UserRoleEnumerations.USER));
            userEntity.setRoleName(
                    this.userRoleService.findRole(UserRoleEnumerations.USER).getRoleEnumerations().name());
        }
        this.userRepository.save(userEntity);


    }

    @Override
    public void addUserLogin(UserLoginServiceModel userLoginServiceModel) {

    }



    @Override
    public boolean existingUsername(String username) {
        return this.userRepository.findByUsername(username).isPresent();
    }

    @Override
    public UserEntity findUserByName(String username) {


        return this.userRepository.findByUsername(username)
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public boolean isAnonymousUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        boolean isAnonymous = true;

        if (!username.equals("anonymousUser")) {
            isAnonymous = false;
        }

        return isAnonymous;

    }

    @Override
    public boolean userIsNotAdmin() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String role = authentication.getAuthorities().toString();
        boolean isNotAdmin = true;
        if (!role.equals("ROLE_USER")) {

            isNotAdmin = false;
        }

        return isNotAdmin;

    }

    @Override
    public UserEntity getUserByAuthentication() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


        String user = authentication.getName();

        return findUserByName(user);

    }





    @Override
    public Optional<UserEntity> findUserByEmail(String email) {


        return this.userRepository.findUserEntityByEmail(email);


    }

    @Override
    public void save(UserEntity user) {
        this.userRepository.save(user);
    }


}
