package com.exhio_api.exhio_api.service;

import com.exhio_api.exhio_api.domain.Vocation;
import com.exhio_api.exhio_api.dto.vocations.CreateVocationDTO;
import com.exhio_api.exhio_api.dto.vocations.ReadVocationDTO;
import com.exhio_api.exhio_api.repository.VocationRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VocationService {

    @Autowired
    private VocationRepository vocationRepository;

    public List<ReadVocationDTO> getVocations() {
        List<Vocation> vocations = vocationRepository.findAll();
        return vocations.stream().map(ReadVocationDTO::new).toList();
    }

    public ReadVocationDTO registerVocation(@Valid CreateVocationDTO vocationDTO) {
        Vocation vocation = new Vocation(vocationDTO);
        Vocation savedVocation = vocationRepository.save(vocation);
        return new ReadVocationDTO(savedVocation);
    }
}
