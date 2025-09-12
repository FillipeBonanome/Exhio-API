package com.exhio_api.exhio_api.service;

import com.exhio_api.exhio_api.domain.Vocation;
import com.exhio_api.exhio_api.dto.vocations.CreateVocationDTO;
import com.exhio_api.exhio_api.dto.vocations.ReadVocationDTO;
import com.exhio_api.exhio_api.dto.vocations.UpdateVocationDTO;
import com.exhio_api.exhio_api.infra.exception.VocationException;
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
        if(vocationRepository.existsByName(vocationDTO.name())) {
            throw new VocationException("Vocation name must be unique");
        }
        Vocation vocation = new Vocation(vocationDTO);
        Vocation savedVocation = vocationRepository.save(vocation);
        return new ReadVocationDTO(savedVocation);
    }

    public ReadVocationDTO getVocationById(Long id) {
        Vocation vocation = vocationRepository.getReferenceById(id);
        return new ReadVocationDTO(vocation);
    }

    public ReadVocationDTO updateVocation(Long id, @Valid UpdateVocationDTO vocationDTO) {
        if(vocationDTO.name() != null && vocationRepository.existsByName(vocationDTO.name())) {
            throw new VocationException("Vocation name must be unique");
        }
        if(vocationDTO.name() != null && vocationDTO.name().isBlank()) {
            throw new VocationException("Vocation name must not be blank");
        }
        Vocation vocation = vocationRepository.getReferenceById(id);
        vocation.updateData(vocationDTO);
        Vocation savedVocation = vocationRepository.save(vocation);
        return new ReadVocationDTO(savedVocation);
    }
}
