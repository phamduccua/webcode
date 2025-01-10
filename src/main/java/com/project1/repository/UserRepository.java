package com.project1.repository;

import com.project1.entity.UserEntity;
import com.project1.model.dto.UserDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByUsernameAndStatus(String phoneNumber,Integer status);
    boolean existsByUsername(String username);
    List<UserEntity> findByUsernameContainingOrFullnameContainingAndRoleAndStatus(String username, String fullname, String role, int status, Pageable pageable);
    List<UserEntity> findByRoleContainingAndStatus(String role,int status,Pageable pageable);
    List<UserEntity> findByUsernameContainingOrFullnameContainingAndRoleAndStatus(String username, String fullname, String role, int status);
    List<UserEntity> findByRoleContainingAndStatus(String role,int status);
    UserEntity findById(Long id);
}
