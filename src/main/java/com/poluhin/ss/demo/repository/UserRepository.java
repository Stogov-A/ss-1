package com.poluhin.ss.demo.repository;

import com.poluhin.ss.demo.domain.entity.RoleEntity;
import com.poluhin.ss.demo.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface UserRepository extends ReactiveMongoRepository<UserEntity, String> {

    Mono<UserEntity> findByName(String name);

    Mono<UserEntity> findByRolesContaining(String role);

    Optional<Object> findUserEntityByRolesContains(RoleEntity roleEntity);
}
