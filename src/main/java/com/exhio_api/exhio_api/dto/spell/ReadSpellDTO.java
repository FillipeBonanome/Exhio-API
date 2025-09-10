package com.exhio_api.exhio_api.dto.spell;

import com.exhio_api.exhio_api.domain.Spell;
import com.exhio_api.exhio_api.domain.Vocation;

import java.util.Set;
import java.util.stream.Collectors;

public record ReadSpellDTO(
        Long id,
        String name,
        String description,
        Set<String> vocations,
        String videoURL
) {
    public ReadSpellDTO(Spell spell) {
        this(
                spell.getId(),
                spell.getName(),
                spell.getDescription(),
                spell.getVocations().stream().map(Vocation::getName).collect(Collectors.toSet()),
                spell.getVideoURL()
        );
    }
}
