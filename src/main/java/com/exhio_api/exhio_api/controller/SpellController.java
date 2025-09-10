package com.exhio_api.exhio_api.controller;

import com.exhio_api.exhio_api.dto.spell.CreateSpellDTO;
import com.exhio_api.exhio_api.dto.spell.ReadSpellDTO;
import com.exhio_api.exhio_api.dto.spell.UpdateSpellDTO;
import com.exhio_api.exhio_api.service.SpellService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/spells")
public class SpellController {

    @Autowired
    private SpellService spellService;

    @GetMapping
    public ResponseEntity<Page<ReadSpellDTO>> getSpells(@PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(spellService.getSpells(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReadSpellDTO> getSpellById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(spellService.getById(id));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ReadSpellDTO> registerSpell(@RequestBody @Valid CreateSpellDTO spellDTO) {
        return ResponseEntity.ok(spellService.registerSpell(spellDTO));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> deleteSpell(@PathVariable(name = "id")Long id) {
        spellService.deleteSpell(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ReadSpellDTO> updateSpell(@PathVariable(name = "id")Long id, @RequestBody UpdateSpellDTO spellDTO) {
        return ResponseEntity.ok(spellService.updateSpell(id, spellDTO));
    }

}
