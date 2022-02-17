package com.epam.medicalsystem.model.entity;

import java.util.Optional;

public enum UserRole {
    NURSE(1),
    DOCTOR(2),
    LABMEM(3),
    REGISTRAR(4),
    ADMIN(5),
    GUEST(6);

    private final int roleId;

    UserRole(int roleId) {
        this.roleId = roleId;
    }

    public int getRoleId() {
        return roleId;
    }

    public static Optional<UserRole> valueOfRoleId(int roleId) {
        for (UserRole e : values()) {
            if (e.roleId == roleId) {
                return Optional.of(e);
            }
        }
        return Optional.empty();
    }
}