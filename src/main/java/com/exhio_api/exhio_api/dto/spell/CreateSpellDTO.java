package com.exhio_api.exhio_api.dto.spell;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.Map;
import java.util.Set;

public record CreateSpellDTO(
        @NotBlank
        String name,
        @NotBlank
        String description,
        Set<String> vocations,
        String videoURL,
        @NotEmpty
        Map<String, Integer> attributes
) {
}
