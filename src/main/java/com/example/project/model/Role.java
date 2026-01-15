package com.example.project.model;

public enum Role {
    GLOBAL_ADMIN,
    ADMIN_WAREHOUSE,
    USER;

    public static Role fromDepartmentName(String departmentName) {
        if (departmentName == null) return Role.USER;

        return switch (departmentName.trim().toUpperCase()) {
            case "WAREHOUSE" -> Role.ADMIN_WAREHOUSE;
            case "IT" -> Role.GLOBAL_ADMIN;
            default -> Role.USER;
        };
    }
}