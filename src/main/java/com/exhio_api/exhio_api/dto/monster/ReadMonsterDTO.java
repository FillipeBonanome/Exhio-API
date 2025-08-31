package com.exhio_api.exhio_api.dto.monster;

import com.exhio_api.exhio_api.domain.Monster;
import com.exhio_api.exhio_api.domain.Resistances;

public record ReadMonsterDTO(
        Long id,
        String name,
        Integer level,
        Integer experience,
        Resistances resistances
        //TODO --> ADD hunts
) {
    public ReadMonsterDTO(Monster monster) {
        this(
                monster.getId(),
                monster.getName(),
                monster.getLevel(),
                monster.getExperience(),
                monster.getResistances()
        );
    }
}
