package com.exhio_api.exhio_api.dto.vocations;

import com.exhio_api.exhio_api.domain.Stats;

public record UpdateVocationDTO(
        String name,
        String description,
        Stats stats
) {
}
