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
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByName(username).blockOptional().orElseThrow();
    }

    public boolean findFirstBySpecificRoles(String roleName) {
//       // Mono<RoleEntity> roleByName = roleService.findRoleByName(roleName);
//      //  if (Boolean.TRUE.equals(roleByName.hasElement().blockOptional().orElse(false))) {
//            return userRepository.findUserEntityByRolesContains(roleByName.block()).isPresent();
//     //   }
        return false;
    }

    public void addNewUser(UserEntity userEntity) {
        if (Boolean.TRUE.equals(userRepository.findByName(userEntity.getName()).hasElement().block())) {
            return;
        }
        userEntity.setPassword(encoder.encode(userEntity.getPassword()));
        userRepository.save(userEntity).block();
    }
}
