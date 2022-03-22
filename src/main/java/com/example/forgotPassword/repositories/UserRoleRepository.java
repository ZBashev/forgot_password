package com.example.forgotPassword.repositories;


import com.example.forgotPassword.models.entities.UserRoleEntity;
import com.example.forgotPassword.models.enumerations.UserRoleEnumerations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, String> {


    Optional<UserRoleEntity> findByRoleEnumerations(UserRoleEnumerations enumeration);
}
