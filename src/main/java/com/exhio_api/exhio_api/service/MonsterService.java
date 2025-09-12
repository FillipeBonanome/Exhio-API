package com.exhio_api.exhio_api.service;

import com.exhio_api.exhio_api.domain.Monster;
import com.exhio_api.exhio_api.dto.monster.CreateMonsterDTO;
import com.exhio_api.exhio_api.dto.monster.ReadMonsterDTO;
import com.exhio_api.exhio_api.dto.monster.UpdateMonsterDTO;
import com.exhio_api.exhio_api.infra.exception.MonsterException;
import com.exhio_api.exhio_api.repository.MonsterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MonsterService {

    @Autowired
    private MonsterRepository monsterRepository;

    public Page<ReadMonsterDTO> readMonsters(Pageable pageable) {
        Page<Monster> monsterPage = monsterRepository.findAllByDeletedFalse(pageable);
        return monsterPage.map(ReadMonsterDTO::new);
    }

    public ReadMonsterDTO readMonster(Long id) {
        Monster monster = monsterRepository.findByIdAndDeletedFalse(id);
        return new ReadMonsterDTO(monster);
    }

    public ReadMonsterDTO registerMonster(CreateMonsterDTO monster) {
        if(monsterRepository.existsByName(monster.name())) {
            throw new MonsterException("Monster's name must be unique'");
        }
        Monster savedMonster = monsterRepository.save(new Monster(monster));
        return new ReadMonsterDTO(savedMonster);
    }

    public ReadMonsterDTO updateMonster(Long id, UpdateMonsterDTO monsterDTO) {
        if(monsterDTO.name() != null && monsterRepository.existsByName(monsterDTO.name())) {
            throw new MonsterException("Monster's name must be unique.");
        }
        if(monsterDTO.name() != null && monsterDTO.name().isBlank()) {
            throw new MonsterException("Monster's name must not be blank");
        }
        Monster monster = monsterRepository.getReferenceById(id);
        monster.updateData(monsterDTO);
        Monster savedMonster = monsterRepository.save(monster);
        return new ReadMonsterDTO(savedMonster);
    }

    public ReadMonsterDTO deleteMonster(Long id) {
        Monster monster = monsterRepository.getReferenceById(id);
        monster.setDeleted(true);
        Monster savedMonster = monsterRepository.save(monster);
        return new ReadMonsterDTO(monster);
    }

    public ReadMonsterDTO restoreMonsterById(Long id) {
        Monster monster = monsterRepository.getReferenceById(id);
        monster.setDeleted(false);
        Monster savedMonster = monsterRepository.save(monster);
        return new ReadMonsterDTO(monster);
    }
}
