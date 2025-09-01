package com.exhio_api.exhio_api.dto.monster;

import com.exhio_api.exhio_api.domain.Monster;
import com.exhio_api.exhio_api.domain.Resistances;
import com.exhio_api.exhio_api.dto.hunt.ReadHuntByMonsterDTO;

import java.util.Set;

public record ReadMonsterByHuntDTO(
        Long id,
        String name,
        Integer level,
        Integer experience,
        Resistances resistances
) {
    public ReadMonsterByHuntDTO(Monster monster) {
        this(
                monster.getId(),
                monster.getName(),
                monster.getLevel(),
                monster.getExperience(),
                monster.getResistances()
        );
    }
}
