package com.exhio_api.exhio_api.repository;

import com.exhio_api.exhio_api.domain.Hunt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HuntRepository extends JpaRepository<Hunt, Long> {
    List<Hunt> findAllByDeletedFalse();
    Hunt getReferenceByIdAndDeletedFalse(Long id);
}
