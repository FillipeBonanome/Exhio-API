package com.exhio_api.exhio_api.dto.user;

import com.exhio_api.exhio_api.domain.Permission;
import com.exhio_api.exhio_api.domain.User;

import java.util.Set;

public record ReadUserDetailedDTO(
        Long id,
        String username,
        String email,
        Set<Permission> permissions
) {
    public ReadUserDetailedDTO(User user) {
        this(
                user.getId(),
                user.getLogin(),
                user.getEmail(),
                user.getPermissions()
        );
    }
}
