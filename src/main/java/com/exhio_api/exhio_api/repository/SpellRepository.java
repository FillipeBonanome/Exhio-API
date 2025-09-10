package com.exhio_api.exhio_api.repository;

import com.exhio_api.exhio_api.domain.Spell;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpellRepository extends JpaRepository<Spell, Long> {
}
