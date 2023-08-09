package com.poluhin.ss.demo.service;

import com.poluhin.ss.demo.domain.entity.RoleEntity;
import com.poluhin.ss.demo.domain.entity.UserEntity;
import com.poluhin.ss.demo.repository.UserRepository;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByName(username).orElseThrow();
    }

    public boolean findFirstBySpecificRoles(String roleName) {
        RoleEntity roleEntity = roleService.findRoleByName(roleName).orElse(null);
        if (Objects.nonNull(roleEntity)) {
            return userRepository.findUserEntityByRolesContains(roleEntity).isPresent();
        }
        return false;
    }

    public void addNewUser(UserEntity userEntity) {
        if (userRepository.findByName(userEntity.getName()).isPresent()) {
            throw new EntityExistsException("User name: " + userEntity.getName() + "is already exist");
        }
        userEntity.setPassword(encoder.encode(userEntity.getPassword()));
        userRepository.save(userEntity);
    }
}
