package ru.lebedev.jwtDemo.domain.security;

public enum Permission {
    MESSAGE_READ("message:read"),
    MESSAGE_WRITE("message:write");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
