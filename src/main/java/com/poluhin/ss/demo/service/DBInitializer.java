package com.poluhin.ss.demo.service;

import com.poluhin.ss.demo.domain.entity.RoleEntity;
import com.poluhin.ss.demo.domain.entity.UserEntity;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import static com.poluhin.ss.demo.service.Role.ROLE_ADMIN;
import static com.poluhin.ss.demo.service.Role.ROLE_USER;

@Service
@RequiredArgsConstructor
public class DBInitializer {

    private final UserService userService;
    private final RoleService roleService;

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
    }

    private boolean adminRoleExists() {
        return roleService.roleExists("ROLE_ADMIN");
    }

    private void addAdminRole() {
        RoleEntity role = new RoleEntity();
        role.setName(ROLE_ADMIN.name());
        roleService.addRole(role);
    }

    private boolean userRoleExists() {
        return roleService.roleExists(ROLE_USER.name());
    }

    private void addUserRole() {
        RoleEntity role = new RoleEntity();
        role.setName(ROLE_USER.name());
        roleService.addRole(role);
    }

    private boolean adminExists() {
        return userService.findFirstBySpecificRoles(ROLE_ADMIN.name());
    }

    private void addAdmin() {
        UserEntity admin = new UserEntity();
        admin.setName("admin");
        admin.setPassword("admin");

        admin.setRoles(roleService.findAll());
        userService.addNewUser(admin);
    }
}
