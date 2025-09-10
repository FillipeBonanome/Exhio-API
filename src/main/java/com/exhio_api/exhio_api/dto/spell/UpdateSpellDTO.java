package com.exhio_api.exhio_api.dto.spell;

import java.util.Map;
import java.util.Set;

public record UpdateSpellDTO(
        String name,
        String description,
        Set<String> vocations,
        String videoURL,
        Map<String, Integer> attributes
) {
}
