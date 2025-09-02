package com.exhio_api.exhio_api.controller;

import com.exhio_api.exhio_api.dto.vocations.CreateVocationDTO;
import com.exhio_api.exhio_api.dto.vocations.ReadVocationDTO;
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

    @PostMapping
    @Transactional
    public ResponseEntity<ReadVocationDTO> registerVocation(@RequestBody @Valid CreateVocationDTO vocationDTO) {
        return ResponseEntity.ok(vocationService.registerVocation(vocationDTO));
    }

}
