package com.exhio_api.exhio_api.repository;

import com.exhio_api.exhio_api.domain.Vocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VocationRepository extends JpaRepository<Vocation, Long> {
    Boolean existsByName(String name);
    Vocation getReferenceByName(String name);
}
