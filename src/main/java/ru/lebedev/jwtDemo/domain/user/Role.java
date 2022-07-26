package ru.lebedev.jwtDemo.domain.user;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ru.lebedev.jwtDemo.domain.security.Permission;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    USER(Set.of(Permission.MESSAGE_READ)),
    ADMIN(Set.of(Permission.MESSAGE_READ, Permission.MESSAGE_WRITE));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
