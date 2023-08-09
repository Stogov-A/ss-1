package com.poluhin.ss.demo.service;

import com.poluhin.ss.demo.domain.entity.RoleEntity;
import com.poluhin.ss.demo.domain.entity.UserEntity;
import com.poluhin.ss.demo.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static com.poluhin.ss.demo.service.Role.ROLE_USER;

@Service
@RequiredArgsConstructor
@Transactional
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleEntity addRole(RoleEntity role){
        return roleRepository.save(role);
    }

    public Set<RoleEntity> findAll(){
        return new HashSet<>(roleRepository.findAll());
    }

    public Optional<RoleEntity> findRoleByName(String name){
        return roleRepository.findByName(name);
    }

    public boolean roleExists(String name){
        return findRoleByName(name).isPresent();
    }

    public RoleEntity getUserRole(){
        return roleRepository.findByName(ROLE_USER.name()).get();
    }

    public UserEntity addToUserRoleUser(UserEntity userEntity){
        Set<RoleEntity>roles = new HashSet<>();
        roles.add(getUserRole());
        userEntity.setRoles(roles);
        return userEntity;
    }
}
