package com.poluhin.ss.demo.service;

import com.poluhin.ss.demo.domain.entity.RoleEntity;
import com.poluhin.ss.demo.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


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

}
