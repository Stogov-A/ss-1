package com.poluhin.ss.demo.repository;

import com.poluhin.ss.demo.domain.entity.RoleEntity;
import com.poluhin.ss.demo.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity>findByName(String name);

    Optional<Object> findUserEntityByRolesContains(RoleEntity roleEntity);
}
