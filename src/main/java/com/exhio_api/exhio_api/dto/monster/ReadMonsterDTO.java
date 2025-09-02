package com.exhio_api.exhio_api.dto.monster;

import com.exhio_api.exhio_api.domain.Monster;
import com.exhio_api.exhio_api.domain.Resistances;
import com.exhio_api.exhio_api.dto.hunt.ReadSimpleHuntDTO;

import java.util.Set;
import java.util.stream.Collectors;

public record ReadMonsterDTO(
        Long id,
        String name,
        Integer level,
        Integer experience,
        Resistances resistances,
        Set<ReadSimpleHuntDTO> hunts
) {
    public ReadMonsterDTO(Monster monster) {
        this(
                monster.getId(),
                monster.getName(),
                monster.getLevel(),
                monster.getExperience(),
                monster.getResistances(),
                monster.getHunts().stream().map(ReadSimpleHuntDTO::new).collect(Collectors.toSet())
        );
    }
}
