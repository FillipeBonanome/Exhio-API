package com.exhio_api.exhio_api.repository;

import com.exhio_api.exhio_api.domain.Dungeon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DungeonRepository extends JpaRepository<Dungeon, Long> {
}
