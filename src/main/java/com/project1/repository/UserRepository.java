package com.project1.repository;

import com.project1.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByUsernameAndStatus(String phoneNumber,Integer status);
    boolean existsByUsername(String username);
}
