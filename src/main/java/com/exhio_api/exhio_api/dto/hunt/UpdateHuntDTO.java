package com.exhio_api.exhio_api.dto.hunt;

import java.util.Set;

public record UpdateHuntDTO(
        String name,
        String description,
        String recommendedLevel,
        String videoURL,
        Boolean premium,
        Set<String> monsters
) {
}
