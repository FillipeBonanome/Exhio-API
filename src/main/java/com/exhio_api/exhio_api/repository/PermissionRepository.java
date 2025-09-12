package com.exhio_api.exhio_api.repository;

import com.exhio_api.exhio_api.domain.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
