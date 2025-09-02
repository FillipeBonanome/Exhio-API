package com.exhio_api.exhio_api.dto.vocations;

import com.exhio_api.exhio_api.domain.Vocation;

public record ReadSimpleVocationDTO(
        Long id,
        String name
) {
    public ReadSimpleVocationDTO(Vocation vocation) {
        this(
                vocation.getId(),
                vocation.getName()
        );
    }
}
