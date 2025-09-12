package com.exhio_api.exhio_api.service;

import com.exhio_api.exhio_api.domain.Dungeon;
import com.exhio_api.exhio_api.domain.Hunt;
import com.exhio_api.exhio_api.domain.Monster;
import com.exhio_api.exhio_api.dto.dungeon.CreateDungeonDTO;
import com.exhio_api.exhio_api.dto.dungeon.ReadDungeonDTO;
import com.exhio_api.exhio_api.dto.dungeon.UpdateDungeonDTO;
import com.exhio_api.exhio_api.infra.exception.DungeonException;
import com.exhio_api.exhio_api.repository.DungeonRepository;
import com.exhio_api.exhio_api.repository.HuntRepository;
import com.exhio_api.exhio_api.repository.MonsterRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class DungeonService {

    @Autowired
    private DungeonRepository dungeonRepository;

    @Autowired
    private HuntRepository huntRepository;

    @Autowired
    private MonsterRepository monsterRepository;

    public ReadDungeonDTO getById(Long id) {
        Dungeon dungeon = dungeonRepository.findById(id).orElseThrow(() -> new DungeonException("Dungeon not found"));
        return new ReadDungeonDTO(dungeon);
    }

    public Page<ReadDungeonDTO> getAll(Pageable pageable) {
        Page<Dungeon> dungeonPage = dungeonRepository.findAll(pageable);
        return dungeonPage.map(ReadDungeonDTO::new);
    }

    public ReadDungeonDTO registerDungeon(CreateDungeonDTO dungeonDTO) {
        Dungeon dungeon = new Dungeon(dungeonDTO);

        for (String name : dungeonDTO.monstersName()) {
            if(monsterRepository.existsByName(name)) {
                dungeon.getMonsters().add(monsterRepository.getReferenceByName(name));
            }
        }

        Hunt hunt = huntRepository.findById(dungeonDTO.huntId()).orElseThrow(() -> new DungeonException("Hunt not found"));
        dungeon.setHunt(hunt);

        Dungeon savedDungeon = dungeonRepository.save(dungeon);
        return new ReadDungeonDTO(savedDungeon);
    }

    public void deleteDungeon(Long id) {
        dungeonRepository.deleteById(id);
    }

    public ReadDungeonDTO updateDungeon(Long id, UpdateDungeonDTO dungeonDTO) {
        Dungeon dungeon = dungeonRepository.findById(id).orElseThrow(() -> new DungeonException("Dungeon not found"));
        dungeon.update(dungeonDTO);
        if(dungeonDTO.huntId() != null) {
            Hunt hunt = huntRepository.findById(dungeonDTO.huntId()).orElseThrow(() -> new DungeonException("Hunt not found"));
            dungeon.setHunt(hunt);
        }
        if(dungeonDTO.monstersName() != null && !dungeonDTO.monstersName().isEmpty()) {
            dungeon.setMonsters(new HashSet<>());
            for(String name : dungeonDTO.monstersName()) {
                if(monsterRepository.existsByName(name)) {
                    Monster monster = monsterRepository.getReferenceByName(name);
                    dungeon.getMonsters().add(monster);
                }
            }
        }
        Dungeon savedDungeon = dungeonRepository.save(dungeon);
        return new ReadDungeonDTO(savedDungeon);
    }
}
