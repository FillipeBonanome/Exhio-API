package com.exhio_api.exhio_api.dto.dungeon;

import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record UpdateDungeonDTO(
        String name,
        Set<String> monstersName,
        Integer requiredLevel,
        Long huntId
) {
}
