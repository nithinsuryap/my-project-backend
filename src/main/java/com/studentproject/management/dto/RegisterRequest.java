package com.studentproject.management.dto;

import com.studentproject.management.entity.Role;

public record RegisterRequest(String username, String password, Role role) {
}
