package com.exhio_api.exhio_api.dto.monster;

import com.exhio_api.exhio_api.domain.Resistances;

public record UpdateMonsterDTO(
        String name,
        Integer level,
        Integer experience,
        Resistances resistances
) {
}
