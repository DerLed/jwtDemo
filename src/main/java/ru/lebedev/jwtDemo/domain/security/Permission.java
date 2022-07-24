package ru.lebedev.jwtDemo.domain.security;

public enum Permission {
    READ("1"), WRITE("2")
    ;
    private final String permission;

    Permission(String permission){
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
