package com.exhio_api.exhio_api.dto.vocations;

import com.exhio_api.exhio_api.domain.Stats;
import com.exhio_api.exhio_api.domain.Vocation;
import com.exhio_api.exhio_api.dto.hunt.ReadSimpleHuntDTO;

import java.util.Set;
import java.util.stream.Collectors;

public record ReadVocationDTO(
        Long id,
        String name,
        String description,
        Set<ReadSimpleHuntDTO> hunts,
        Stats stats
) {
    public ReadVocationDTO(Vocation vocation) {
        this(
                vocation.getId(),
                vocation.getName(),
                vocation.getDescription(),
                vocation.getHunts().stream().map(ReadSimpleHuntDTO::new).collect(Collectors.toSet()),
                vocation.getStats()
        );
    }
}
