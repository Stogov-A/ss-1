package com.poluhin.ss.demo.domain.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

//@Document(value = "roles")
@Getter
@Setter
@AllArgsConstructor
@Data
public class RoleEntity implements GrantedAuthority {
    @Id
    private String id;

    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
