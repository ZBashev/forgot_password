package com.example.forgotPassword.security;


import com.example.forgotPassword.models.entities.UserEntity;
import com.example.forgotPassword.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BoilSecurityDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    public BoilSecurityDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        UserEntity userEntity = this.userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User with name" + username + "was not found")
        );


        return mapToUserDetails(userEntity);

    }

    private UserDetails mapToUserDetails(UserEntity userEntity) {

        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();


        grantedAuthorityList.add(new SimpleGrantedAuthority(
                "ROLE_" + userEntity.getUserRole().getRoleEnumerations().name()));


        return new User(
                userEntity.getUsername(),
                userEntity.getPassword(),
                grantedAuthorityList);


    }
}
