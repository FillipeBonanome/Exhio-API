package com.exhio_api.exhio_api.dto.dungeon;

import com.exhio_api.exhio_api.domain.Dungeon;
import com.exhio_api.exhio_api.dto.hunt.ReadSimpleHuntDTO;
import com.exhio_api.exhio_api.dto.monster.ReadSimpleMonsterDTO;

import java.util.List;

public record ReadDungeonDTO(
        String name,
        List<ReadSimpleMonsterDTO> monsters,
        Integer requiredLevel,
        ReadSimpleHuntDTO hunt
) {
    public ReadDungeonDTO(Dungeon dungeon) {
        this(
                dungeon.getName(),
                dungeon.getMonsters().stream().map(ReadSimpleMonsterDTO::new).toList(),
                dungeon.getRequiredLevel(),
                new ReadSimpleHuntDTO(dungeon.getHunt())
        );
    }
}
