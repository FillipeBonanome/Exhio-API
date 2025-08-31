package com.exhio_api.exhio_api.controller;

import com.exhio_api.exhio_api.dto.monster.CreateMonsterDTO;
import com.exhio_api.exhio_api.dto.monster.ReadMonsterDTO;
import com.exhio_api.exhio_api.dto.monster.UpdateMonsterDTO;
import com.exhio_api.exhio_api.service.MonsterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/monsters")
public class MonsterController {

    @Autowired
    private MonsterService monsterService;

    @GetMapping
    public ResponseEntity<Page<ReadMonsterDTO>> getMonsters(@PageableDefault(size = 10, sort = {"level"}) Pageable pageable) {
        return ResponseEntity.ok(monsterService.readMonsters(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReadMonsterDTO> getMonsterById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(monsterService.readMonster(id));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ReadMonsterDTO> registerMonster(@Valid @RequestBody CreateMonsterDTO monster) {
        return ResponseEntity.ok(monsterService.registerMonster(monster));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ReadMonsterDTO> updateMonsterById(@PathVariable(name = "id") Long id, @RequestBody UpdateMonsterDTO monsterDTO) {
        return ResponseEntity.ok(monsterService.updateMonster(id, monsterDTO));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<ReadMonsterDTO>deleteMonsterById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(monsterService.deleteMonster(id));
    }

    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity<ReadMonsterDTO> restoreMonsterById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(monsterService.restoreMonsterById(id));
    }

}
