package com.exhio_api.exhio_api.service;

import com.exhio_api.exhio_api.domain.Permission;
import com.exhio_api.exhio_api.infra.exception.PermissionException;
import com.exhio_api.exhio_api.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    public Permission getById(Long id) {
        return permissionRepository.findById(id).orElseThrow(() -> new PermissionException("Permission not found"));
    }

    public Permission getByName(String name) {
        return permissionRepository.findByName(name).orElseThrow(() -> new PermissionException("Permission not found"));
    }

    public Boolean existsByName(String name) {
        return permissionRepository.existsByName(name);
    }

}
