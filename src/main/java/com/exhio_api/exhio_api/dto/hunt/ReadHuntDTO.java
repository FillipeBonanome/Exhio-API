package com.exhio_api.exhio_api.dto.hunt;

import com.exhio_api.exhio_api.domain.Hunt;
import com.exhio_api.exhio_api.dto.dungeon.ReadDungeonDTO;
import com.exhio_api.exhio_api.dto.monster.ReadSimpleMonsterDTO;
import com.exhio_api.exhio_api.dto.quest.ReadSimpleQuestDTO;
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
        Set<ReadSimpleMonsterDTO> monsters,
        Set<ReadSimpleVocationDTO> vocations,
        Set<ReadSimpleQuestDTO> quests,
        Set<ReadDungeonDTO> dungeons
) {
    public ReadHuntDTO(Hunt hunt) {
        this(
                hunt.getId(),
                hunt.getName(),
                hunt.getDescription(),
                hunt.getRecommendedLevel(),
                hunt.getVideoURL(),
                hunt.getPremium(),
                hunt.getMonsters().stream().map(ReadSimpleMonsterDTO::new).collect(Collectors.toSet()),
                hunt.getVocations().stream().map(ReadSimpleVocationDTO::new).collect(Collectors.toSet()),
                hunt.getQuests().stream().map(ReadSimpleQuestDTO::new).collect(Collectors.toSet()),
                hunt.getDungeons().stream().map(ReadDungeonDTO::new).collect(Collectors.toSet())
        );
    }
}
