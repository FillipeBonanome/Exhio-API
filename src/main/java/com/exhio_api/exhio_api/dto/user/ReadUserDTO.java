package com.exhio_api.exhio_api.dto.user;

import com.exhio_api.exhio_api.domain.User;

public record ReadUserDTO(
        Long id,
        String username,
        String email
) {
    public ReadUserDTO(User savedUser) {
        this(savedUser.getId(), savedUser.getUsername(), savedUser.getEmail());
    }
}
