package com.exhio_api.exhio_api.dto.dungeon;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record CreateDungeonDTO(
        @NotBlank
        String name,
        @NotEmpty
        Set<String> monstersName,
        @NotNull
        Integer requiredLevel,
        @NotNull
        Long huntId
) {
}
