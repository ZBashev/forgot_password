package com.example.forgotPassword.repositories;


import com.example.forgotPassword.models.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {


    Optional<UserEntity> findByUsername(String username);


    Optional<UserEntity> findUserEntityByEmail(String email);

}
