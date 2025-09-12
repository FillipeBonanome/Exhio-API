package com.exhio_api.exhio_api.dto.user;

import jakarta.validation.constraints.NotEmpty;

import java.util.Set;

public record UpdateUserPermissionDTO(
        @NotEmpty
        Set<String> permissions
) {
}
