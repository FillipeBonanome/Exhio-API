package com.exhio_api.exhio_api.controller;

import com.exhio_api.exhio_api.dto.dungeon.CreateDungeonDTO;
import com.exhio_api.exhio_api.dto.dungeon.ReadDungeonDTO;
import com.exhio_api.exhio_api.dto.dungeon.UpdateDungeonDTO;
import com.exhio_api.exhio_api.service.DungeonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dungeons")
public class DungeonController {

    @Autowired
    private DungeonService dungeonService;

    @GetMapping("/{id}")
    public ResponseEntity<ReadDungeonDTO> getById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(dungeonService.getById(id));
    }

    @GetMapping
    public ResponseEntity<Page<ReadDungeonDTO>> getAll(Pageable pageable) {
        return ResponseEntity.ok(dungeonService.getAll(pageable));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ReadDungeonDTO> registerDungeon(@RequestBody @Valid CreateDungeonDTO dungeonDTO) {
        return ResponseEntity.ok(dungeonService.registerDungeon(dungeonDTO));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> deleteDungeon(@PathVariable(name = "id") Long id) {
        dungeonService.deleteDungeon(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ReadDungeonDTO> updateDungeon(@PathVariable(name = "id") Long id, @RequestBody UpdateDungeonDTO dungeonDTO) {
        return ResponseEntity.ok(dungeonService.updateDungeon(id, dungeonDTO));
    }
}
