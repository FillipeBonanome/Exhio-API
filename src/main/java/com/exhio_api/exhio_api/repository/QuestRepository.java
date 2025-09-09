package com.exhio_api.exhio_api.repository;

import com.exhio_api.exhio_api.domain.Quest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestRepository extends JpaRepository<Quest, Long> {

}
