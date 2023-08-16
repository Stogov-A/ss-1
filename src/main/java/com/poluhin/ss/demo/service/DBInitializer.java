package com.poluhin.ss.demo.service;

import com.poluhin.ss.demo.domain.entity.RoleEntity;
import com.poluhin.ss.demo.domain.entity.UserEntity;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.poluhin.ss.demo.domain.entity.Role.ROLE_ADMIN;
import static com.poluhin.ss.demo.domain.entity.Role.ROLE_USER;

@Service
@RequiredArgsConstructor
public class DBInitializer {

    private final UserService userService;

    @PostConstruct
    private void initDatabase() {
        if (!adminRoleExists()) {
            addAdminRole();
        }

        if (!userRoleExists()) {
            addUserRole();
        }

        if (!adminExists()) {
            addAdmin();
        }
        addUser();
    }

    private void addUser() {
        UserEntity user = new UserEntity();
        user.setName("user");
        user.setPassword("user");

        List<RoleEntity> roleEntitySet = new ArrayList<>();
        roleEntitySet.add(new RoleEntity("", ROLE_USER.name()));
        user.setRoles(roleEntitySet);
        userService.addNewUser(user);
    }

    private boolean adminRoleExists() {
    //    return Boolean.TRUE.equals(roleService.roleExists("ROLE_ADMIN").hasElement().block());
        return false;
    }

    private void addAdminRole() {
//        RoleEntity role = new RoleEntity();
//        role.setName(ROLE_ADMIN.name());
//        roleService.addRole(role);
    }

    private boolean userRoleExists() {
       // return Boolean.TRUE.equals(roleService.roleExists(ROLE_USER.name()).hasElement().block());
        return false;
    }

    private void addUserRole() {
//        RoleEntity role = new RoleEntity();
//        role.setName(ROLE_USER.name());
//        roleService.addRole(role);
    }

    private boolean adminExists() {
       // return userService.findFirstBySpecificRoles(ROLE_ADMIN.name());
        return false;
    }

    private void addAdmin() {
        UserEntity admin = new UserEntity();
        admin.setName("admin");
        admin.setPassword("admin");

        List<RoleEntity> roleEntitySet = new ArrayList<>();
        roleEntitySet.add(new RoleEntity("", ROLE_ADMIN.name()));
        admin.setRoles(roleEntitySet);
        userService.addNewUser(admin);
    }
}
