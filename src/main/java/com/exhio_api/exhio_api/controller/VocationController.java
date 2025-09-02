package com.exhio_api.exhio_api.controller;

import com.exhio_api.exhio_api.dto.vocations.CreateVocationDTO;
import com.exhio_api.exhio_api.dto.vocations.ReadVocationDTO;
import com.exhio_api.exhio_api.dto.vocations.UpdateVocationDTO;
import com.exhio_api.exhio_api.service.VocationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vocations")
public class VocationController {

    @Autowired
    private VocationService vocationService;

    @GetMapping
    public ResponseEntity<List<ReadVocationDTO>> getVocations() {
        return ResponseEntity.ok(vocationService.getVocations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReadVocationDTO> getVocationById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(vocationService.getVocationById(id));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ReadVocationDTO> registerVocation(@RequestBody @Valid CreateVocationDTO vocationDTO) {
        return ResponseEntity.ok(vocationService.registerVocation(vocationDTO));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ReadVocationDTO> updateVocation(@PathVariable(name = "id") Long id, @RequestBody @Valid UpdateVocationDTO vocationDTO) {
        return ResponseEntity.ok(vocationService.updateVocation(id, vocationDTO));
    }
    


}
