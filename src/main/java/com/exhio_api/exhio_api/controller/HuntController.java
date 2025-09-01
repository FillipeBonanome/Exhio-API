package com.exhio_api.exhio_api.controller;

import com.exhio_api.exhio_api.dto.hunt.CreateHuntByMonsterIdDTO;
import com.exhio_api.exhio_api.dto.hunt.CreateHuntByMonsterNameDTO;
import com.exhio_api.exhio_api.dto.hunt.ReadHuntDTO;
import com.exhio_api.exhio_api.dto.hunt.UpdateHuntDTO;
import com.exhio_api.exhio_api.service.HuntService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hunts")
public class HuntController {

    @Autowired
    private HuntService huntService;

    @GetMapping
    public ResponseEntity<List<ReadHuntDTO>> getHunts() {
        return ResponseEntity.ok(huntService.getHunts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReadHuntDTO> getHuntById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(huntService.getHuntById(id));
    }

    @PostMapping("/id")
    @Transactional
    public ResponseEntity<ReadHuntDTO> registerHunt(@Valid @RequestBody CreateHuntByMonsterIdDTO hunt) {
        return ResponseEntity.ok(huntService.registerById(hunt));
    }

    @PostMapping("/name")
    @Transactional
    public ResponseEntity<ReadHuntDTO> registerHuntByName(@Valid @RequestBody CreateHuntByMonsterNameDTO hunt) {
        return ResponseEntity.ok(huntService.registerByName(hunt));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ReadHuntDTO> updateHunt(@PathVariable(name = "id") Long id, @Valid @RequestBody UpdateHuntDTO hunt) {
        return ResponseEntity.ok(huntService.updateHunt(id, hunt));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<ReadHuntDTO> deleteHunt(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(huntService.deleteHunt(id));
    }


}
