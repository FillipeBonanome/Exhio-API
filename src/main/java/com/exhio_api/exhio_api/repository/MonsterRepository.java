package com.exhio_api.exhio_api.repository;

import com.exhio_api.exhio_api.domain.Monster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonsterRepository extends JpaRepository<Monster, Long> {
    Page<Monster> findAllByDeletedFalse(Pageable pageable);
    Monster findByIdAndDeletedFalse(Long id);
}
