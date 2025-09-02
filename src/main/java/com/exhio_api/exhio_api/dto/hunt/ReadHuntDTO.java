package com.exhio_api.exhio_api.dto.hunt;

import com.exhio_api.exhio_api.domain.Hunt;
import com.exhio_api.exhio_api.dto.monster.ReadMonsterByHuntDTO;
import com.exhio_api.exhio_api.dto.monster.ReadMonsterDTO;
import com.exhio_api.exhio_api.dto.vocations.ReadSimpleVocationDTO;

import java.util.Set;
import java.util.stream.Collectors;

public record ReadHuntDTO(
        Long id,
        String name,
        String description,
        String recommendedLevel,
        String videoURL,
        Boolean premium,
        Set<ReadMonsterByHuntDTO> monsters,
        Set<ReadSimpleVocationDTO> vocations
) {
    public ReadHuntDTO(Hunt hunt) {
        this(
                hunt.getId(),
                hunt.getName(),
                hunt.getDescription(),
                hunt.getRecommendedLevel(),
                hunt.getVideoURL(),
                hunt.getPremium(),
                hunt.getMonsters().stream().map(ReadMonsterByHuntDTO::new).collect(Collectors.toSet()),
                hunt.getVocations().stream().map(ReadSimpleVocationDTO::new).collect(Collectors.toSet())
        );
    }
}
