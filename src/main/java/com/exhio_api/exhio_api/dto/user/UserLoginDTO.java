package com.exhio_api.exhio_api.dto.user;

import jakarta.validation.constraints.NotBlank;

public record UserLoginDTO(
        @NotBlank
        String login,
        @NotBlank
        String password
) {
}
