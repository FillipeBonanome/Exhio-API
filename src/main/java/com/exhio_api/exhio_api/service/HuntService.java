package com.exhio_api.exhio_api.service;

import com.exhio_api.exhio_api.domain.Hunt;
import com.exhio_api.exhio_api.domain.Monster;
import com.exhio_api.exhio_api.domain.Vocation;
import com.exhio_api.exhio_api.dto.hunt.CreateHuntByMonsterIdDTO;
import com.exhio_api.exhio_api.dto.hunt.CreateHuntByMonsterNameDTO;
import com.exhio_api.exhio_api.dto.hunt.ReadHuntDTO;
import com.exhio_api.exhio_api.dto.hunt.UpdateHuntDTO;
import com.exhio_api.exhio_api.infra.exception.HuntException;
import com.exhio_api.exhio_api.repository.HuntRepository;
import com.exhio_api.exhio_api.repository.MonsterRepository;
import com.exhio_api.exhio_api.repository.VocationRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class HuntService {

    @Autowired
    private HuntRepository huntRepository;

    @Autowired
    private MonsterRepository monsterRepository;

    @Autowired
    private VocationRepository vocationRepository;

    public List<ReadHuntDTO> getHunts() {
        List<Hunt> hunts = huntRepository.findAllByDeletedFalse();
        return hunts.stream().map(ReadHuntDTO::new).toList();
    }

    public ReadHuntDTO registerById(@Valid CreateHuntByMonsterIdDTO huntDTO) {
        Hunt hunt = new Hunt(huntDTO);
        for (Long id : huntDTO.monsters()) {
            if(monsterRepository.existsById(id)) {
                Monster monster = monsterRepository.getReferenceById(id);
                hunt.getMonsters().add(monster);
            }
        }
        Hunt savedHunt = huntRepository.save(hunt);
        return new ReadHuntDTO(savedHunt);
    }

    public ReadHuntDTO registerByName(@Valid CreateHuntByMonsterNameDTO huntDTO) {
        Hunt hunt = new Hunt(huntDTO);
        for (String name : huntDTO.monsters()) {
            if(monsterRepository.existsByName(name)) {
                Monster monster = monsterRepository.getReferenceByName(name);
                hunt.getMonsters().add(monster);
            }
        }

        if(huntDTO.vocations() != null && !huntDTO.vocations().isEmpty()) {
            for(String name : huntDTO.vocations()) {
                if(vocationRepository.existsByName(name)) {
                    Vocation vocation = vocationRepository.getReferenceByName(name);
                    hunt.getVocations().add(vocation);
                }
            }
        }

        Hunt savedHunt = huntRepository.save(hunt);
        return new ReadHuntDTO(savedHunt);
    }

    public ReadHuntDTO updateHunt(Long id, @Valid UpdateHuntDTO huntDTO) {
        Hunt hunt = huntRepository.getReferenceByIdAndDeletedFalse(id);
        hunt.updateData(huntDTO);

        if(huntDTO.monsters() != null && !huntDTO.monsters().isEmpty()) {
            hunt.setMonsters(new HashSet<>());
            for(String name : huntDTO.monsters()) {
                if (monsterRepository.existsByName(name)) {
                    Monster monster =  monsterRepository.getReferenceByName(name);
                    hunt.getMonsters().add(monster);
                }
            }
        }

        if(huntDTO.vocations() != null && !huntDTO.vocations().isEmpty()) {
            hunt.setVocations(new HashSet<>());
            for(String name : huntDTO.vocations()) {
                if(vocationRepository.existsByName(name)) {
                    Vocation vocation = vocationRepository.getReferenceByName(name);
                    hunt.getVocations().add(vocation);
                }
            }
        }

        Hunt savedHunt = huntRepository.save(hunt);
        return new ReadHuntDTO(savedHunt);

    }

    public ReadHuntDTO deleteHunt(Long id) {
        Hunt hunt = huntRepository.getReferenceByIdAndDeletedFalse(id);
        hunt.setDeleted(true);
        Hunt savedHunt = huntRepository.save(hunt);
        return new ReadHuntDTO(savedHunt);
    }

    public ReadHuntDTO getHuntById(Long id) {
        Hunt hunt = huntRepository.getReferenceByIdAndDeletedFalse(id);
        if(hunt == null) {
            throw new HuntException("Hunt not found");
        }
        return new ReadHuntDTO(hunt);
    }
}
