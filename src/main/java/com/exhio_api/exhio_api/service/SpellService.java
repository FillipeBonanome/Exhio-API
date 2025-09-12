package com.exhio_api.exhio_api.service;

import com.exhio_api.exhio_api.domain.Spell;
import com.exhio_api.exhio_api.domain.Vocation;
import com.exhio_api.exhio_api.dto.spell.CreateSpellDTO;
import com.exhio_api.exhio_api.dto.spell.ReadSpellDTO;
import com.exhio_api.exhio_api.dto.spell.UpdateSpellDTO;
import com.exhio_api.exhio_api.infra.exception.SpellException;
import com.exhio_api.exhio_api.repository.SpellRepository;
import com.exhio_api.exhio_api.repository.VocationRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class SpellService {

    @Autowired
    private SpellRepository spellRepository;

    @Autowired
    private VocationRepository vocationRepository;


    public Page<ReadSpellDTO> getSpells(Pageable pageable) {
        Page<Spell> spellPage = spellRepository.findAll(pageable);
        return spellPage.map(ReadSpellDTO::new);
    }

    public ReadSpellDTO getById(Long id) {
        Spell spell = spellRepository.findById(id).orElseThrow(() -> new SpellException("Spell not found"));
        return new ReadSpellDTO(spell);
    }

    public ReadSpellDTO registerSpell(@Valid CreateSpellDTO spellDTO) {
        Spell spell = new Spell(spellDTO);
        for(String name : spellDTO.vocations()) {
            if(vocationRepository.existsByName(name)) {
               Vocation vocation = vocationRepository.getReferenceByName(name);
               spell.getVocations().add(vocation);
            }
        }
        Spell savedSpell = spellRepository.save(spell);
        return new ReadSpellDTO(savedSpell);
    }

    public ReadSpellDTO updateSpell(Long id, UpdateSpellDTO spellDTO) {
        Spell spell = spellRepository.findById(id).orElseThrow(() -> new SpellException("Spell not found"));
        spell.update(spellDTO);
        if(spellDTO.vocations() != null) {
            spell.setVocations(new HashSet<>());
            for(String name : spellDTO.vocations()) {
                if(vocationRepository.existsByName(name)) {
                    spell.getVocations().add(vocationRepository.getReferenceByName(name));
                }
            }
        }
        Spell savedSpell = spellRepository.save(spell);
        return new ReadSpellDTO(savedSpell);
    }

    public void deleteSpell(Long id) {
        spellRepository.deleteById(id);
    }
}
