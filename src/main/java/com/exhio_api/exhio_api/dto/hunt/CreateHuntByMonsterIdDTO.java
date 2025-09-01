package com.exhio_api.exhio_api.dto.hunt;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record CreateHuntByMonsterIdDTO(
        @NotBlank
        String name,
        @NotBlank
        String description,
        @NotBlank
        String recommendedLevel,
        String videoURL,
        @NotNull
        Boolean premium,
        Set<Long> monsters
) {
}
