package com.exhio_api.exhio_api.dto.monster;

import com.exhio_api.exhio_api.domain.Resistances;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateMonsterDTO(
        @NotBlank
        String name,
        @NotNull
        @Positive(message = "Monster level should be higher than 0")
        Integer level,
        @NotNull
        @Positive(message = "Monster experience should be higher than 0")
        Integer experience,
        @Valid
        Resistances resistances
) {
}
