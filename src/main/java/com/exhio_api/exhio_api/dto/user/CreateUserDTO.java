package com.exhio_api.exhio_api.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateUserDTO(
        @NotBlank
        String login,
        @NotBlank
        String password,
        @Email
        String email
) {
}
