package com.exhio_api.exhio_api.repository;

import com.exhio_api.exhio_api.domain.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
    Optional<Permission> findByName(String name);
    Boolean existsByName(String name);
}
