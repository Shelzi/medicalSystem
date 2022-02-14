package com.epam.medicalsystem.model.entity;

public enum UserRole {
    NURSE(1),
    DOCTOR(2),
    ADMIN(3),
    REGISTRAR(4),
    LABMEM(5);

    private final int roleId;

    UserRole(int roleId) {
        this.roleId = roleId;
    }

    public int getRoleId() {
        return roleId;
    }
}